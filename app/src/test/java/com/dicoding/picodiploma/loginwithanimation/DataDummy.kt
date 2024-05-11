package com.dicoding.picodiploma.loginwithanimation

import com.dicoding.picodiploma.loginwithanimation.data.remote.response.ListStoryItem

object DataDummy {

    fun generateDummyStoryResponse(): List<ListStoryItem> {
        val items: MutableList<ListStoryItem> = arrayListOf()
        for (i in 0..100) {
            val story = ListStoryItem(
                i.toString(),
                "$i",
                "$i",
                "$i",
               i.toDouble()*10,
                "$i",
                i.toDouble()*10,
            )
            items.add(story)
        }
        return items
    }
}