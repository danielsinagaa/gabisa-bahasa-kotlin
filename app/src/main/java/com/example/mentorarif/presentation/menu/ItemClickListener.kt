package com.example.mentorarif.presentation.menu

import com.example.mentorarif.data.model.Item

interface ItemClickListener {
    fun onDelete(item: Item)
    fun onEdit(item: Item)
}