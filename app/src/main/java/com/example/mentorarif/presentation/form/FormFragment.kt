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
import com.example.mentorarif.data.model.Item
import com.example.mentorarif.data.repository.ItemRepository
import com.example.mentorarif.databinding.FragmentFormBinding
import kotlinx.android.synthetic.main.fragment_form.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding
    private lateinit var viewModel: FormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormBinding.inflate(layoutInflater)

        binding.apply {
            inputBtn.setOnClickListener{
                val formatter: NumberFormat = DecimalFormat("#,###")
                val price = priceInput.text.toString().toInt()
                val formattedPrice: String = formatter.format(price)

                val item = Item(
                    name = itemNameInput.text.toString(),
                    note = noteInput.text.toString(),
                    quantity = quantityInput.text.toString(),
                    price = formattedPrice,
                    date = dateInput.text.toString(),
                    id = ""
                )

                viewModel.save(item)
                itemNameInput.text?.clear()
                noteInput.text?.clear()
                quantityInput.text?.clear()
                priceInput.text?.clear()
                dateInput.text?.clear()
            }
            cancelBtn.setOnClickListener {
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