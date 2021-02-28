package com.example.mentorarif.data.repository

import com.example.mentorarif.data.model.Item
import java.util.*

class ItemRepository : ItemRepositoryInterface {
    companion object {
        var itemList = arrayListOf(
            Item(
                UUID.randomUUID().toString(),
                "1/1/2020",
                "111",
                "aaa",
                "111",
                "note1"
            ),
            Item(
                UUID.randomUUID().toString(),
                "2/1/2020",
                "222",
                "bbb",
                "222",
                "note2"
            ),
            Item(
                UUID.randomUUID().toString(),
                "1/3/2020",
                "333",
                "ccc",
                "333",
                "note3"
            )
        )
    }

    override fun list(): List<Item> = itemList

    override fun save(item: Item): Item {
        if(item.id.equals("")){
            item.id = UUID.randomUUID().toString()
            itemList.add(item)
        }else {
            val data = itemList.filter {
                it.id == item.id
            }
            val index = itemList.indexOf(data.single())
            itemList[index] = item
        }
        return item
    }

    override fun delete(item: Item): Item {
        itemList.removeAt(itemList.indexOf(item))
        return item
    }

    override fun findByItem(item: Item): Item = itemList.get(itemList.indexOf(item))

}