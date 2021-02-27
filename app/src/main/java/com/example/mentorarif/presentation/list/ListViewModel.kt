package com.example.mentorarif.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mentorarif.data.model.Item
import com.example.mentorarif.data.repository.ItemRepositoryInterface

class ListViewModel(private val repository: ItemRepositoryInterface): ViewModel() {
    private var _itemLiveData = MutableLiveData<List<Item>>()

    val itemLiveData: LiveData<List<Item>>
        get() {
            loadItemData()
            return _itemLiveData
        }

    private fun loadItemData(){
        _itemLiveData.value = repository.list()
    }
}