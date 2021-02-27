package com.example.mentorarif.presentation.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihanframgent.list.ListViewAdapter
import com.example.mentorarif.data.repository.ItemRepository
import com.example.mentorarif.databinding.FragmentListBinding

class ListFragment : Fragment() {

    lateinit var rvAdapter : ListViewAdapter
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: FragmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        binding.apply {
            rvAdapter = ListViewAdapter()
            itemRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = rvAdapter
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository = ItemRepository()
                return ListViewModel(repository) as T
            }
        }).get(ListViewModel::class.java)
    }

    private fun subscribe() {
        viewModel.itemLiveData.observe(this) {
            rvAdapter.setData(it)
        }
    }
}