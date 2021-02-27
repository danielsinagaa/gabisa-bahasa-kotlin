package com.example.myapplication.presentation.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mentorarif.data.model.Item
import com.example.mentorarif.databinding.CardViewItemListBinding

class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = CardViewItemListBinding.bind(view)

    fun bind(item: Item) {
        binding.apply {
            itemView.text = item.name
            quantityView.text = item.quantity
            priceView.text = item.price
            dateView.text = item.date
            noteView.text = item.note
        }
    }
}