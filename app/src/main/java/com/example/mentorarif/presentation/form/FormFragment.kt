package com.example.mentorarif.presentation.form

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mentorarif.R
import com.example.mentorarif.data.model.Item
import com.example.mentorarif.data.repository.ItemRepository
import com.example.mentorarif.databinding.FragmentFormBinding
import kotlinx.android.synthetic.main.fragment_form.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class FormFragment : Fragment() {

    private var item : Item? = null
    private lateinit var binding: FragmentFormBinding
    private lateinit var viewModel: FormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            item = it.getParcelable("edit_item")
        }

        initViewModel()
        subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormBinding.inflate(layoutInflater)

        binding.apply {

            item?.apply {
                inputBtn.text = "UPDATE"
                formTitle.text = "Edit "
                dateTi.editText?.setText(date)
                nameTi.editText?.setText(name)
                priceTi.editText?.setText(price)
                quantityTi.editText?.setText(quantity)
                noteTi.editText?.setText(note)
            }

            inputBtn.setOnClickListener{

                val formatter: NumberFormat = DecimalFormat("#,###")

                if (item == null){

                    item = Item(
                            name = itemNameInput.text.toString(),
                            note = noteInput.text.toString(),
                            quantity = quantityInput.text.toString(),
                            price = formatter.format(priceInput.text.toString().toInt()),
                            date = dateInput.text.toString(),
                            id = ""
                    )
                } else {
                    item?.let {
                        item = Item(
                                id = it.id,
                                name = itemNameInput.text.toString(),
                                note = noteInput.text.toString(),
                                quantity = quantityInput.text.toString(),
                                price = formatter.format(priceInput.text.toString().toInt()),
                                date = dateInput.text.toString()
                        )
                    }
                }

                viewModel.save(item!!)
                itemNameInput.text?.clear()
                noteInput.text?.clear()
                quantityInput.text?.clear()
                priceInput.text?.clear()
                dateInput.text?.clear()
            }

            if (inputBtn.text == "UPDATE"){
                cancelBtn.setOnClickListener {
                    Navigation.findNavController(requireView()).navigate(R.id.action_formFragment_to_listFragment)
                }
            } else cancelBtn.setOnClickListener {
                Navigation.findNavController(requireView()).popBackStack()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        dateInput.setInputType(InputType.TYPE_NULL)
        dateInput.setOnClickListener(View.OnClickListener {
            val datePickerDialog = activity?.let { it1 ->
                DatePickerDialog(
                    it1, DatePickerDialog.OnDateSetListener
                    { view, year, monthOfYear, dayOfMonth ->
                        dateInput.setText(
                            "$year/$monthOfYear/$dayOfMonth",
                            TextView.BufferType.EDITABLE
                        );
                    }, year, month, day
                )
            }
            datePickerDialog?.show()
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository = ItemRepository()
                return FormViewModel(repository) as T
            }
        }).get(FormViewModel::class.java)
    }

    private fun subscribe() {
        viewModel._itemLiveData.observe(this) {
            Log.d("Subcribe", "$it")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FormFragment()
    }
}