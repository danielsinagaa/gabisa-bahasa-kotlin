package com.example.mentorarif.presentation.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mentorarif.data.model.Item
import com.example.mentorarif.data.repository.ItemRepositoryInterface

class FormViewModel(private val repository: ItemRepositoryInterface): ViewModel()  {
    var _itemLiveData = MutableLiveData<Item>()

    val itemLiveData: LiveData<Item>
        get() {
            return _itemLiveData
        }

    fun save(item: Item){
        _itemLiveData.value = repository.save(item)
    }
}