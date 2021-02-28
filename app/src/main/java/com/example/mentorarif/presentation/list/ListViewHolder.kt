package com.example.myapplication.presentation.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mentorarif.data.model.Item
import com.example.mentorarif.databinding.CardViewItemListBinding
import com.example.mentorarif.presentation.menu.ItemClickListener

class ListViewHolder(view: View, private val itemClickListener: ItemClickListener) : RecyclerView.ViewHolder(view) {
    private val binding = CardViewItemListBinding.bind(view)

    fun bind(item: Item) {
        binding.apply {
            itemView.text = item.name
            quantityView.text = item.quantity
            priceView.text = item.price
            dateView.text = item.date
            noteView.text = item.note

            deleteView.setOnClickListener {
                itemClickListener.onDelete(item)
            }

            editView.setOnClickListener {
                itemClickListener.onEdit(item)
            }
        }
    }
}