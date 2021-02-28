package com.example.mentorarif.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mentorarif.data.model.Item
import com.example.mentorarif.data.repository.ItemRepositoryInterface
import com.example.mentorarif.presentation.menu.ItemClickListener

class ListViewModel(private val repository: ItemRepositoryInterface): ViewModel(), ItemClickListener {
    private var _itemLiveData = MutableLiveData<List<Item>>()
    private var __itemLiveData = MutableLiveData<Item>()

    val itemLiveData: LiveData<List<Item>>
        get() {
            loadItemData()
            return _itemLiveData
        }

    val itemLiveData_: LiveData<Item>
        get() {
            return __itemLiveData
        }

    private fun loadItemData(){
        _itemLiveData.value = repository.list()
    }

    private fun loadItem(item: Item){
        __itemLiveData.value = repository.findByItem(item)
    }

    override fun onDelete(item: Item) {
        repository.delete(item)
        loadItemData()
    }

    override fun onEdit(item: Item){
        loadItem(item)
    }
}