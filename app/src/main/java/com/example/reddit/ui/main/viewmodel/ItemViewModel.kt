package com.example.reddit.ui.main.viewmodel

import androidx.databinding.ObservableField
import com.example.reddit.data.model.Post
import com.example.reddit.ui.base.BaseViewModel
import com.example.reddit.utils.getTimeAgo
import java.util.*

class ItemViewModel : BaseViewModel() {

    fun initPost(post: Post) {
        title.set(post.title)
        author.set("Posted by " + post.author)
        subreddit_name_prefixed.set(post.subreddit_name_prefixed)
        date.set(getDateTime(post.postDate))
        thumbnail.set(post.thumbnail)
        score.set(post.score.toString())
        num_comments.set(post.num_comments.toString())
        permalink.set(post.permalink)
    }
    var title: ObservableField<String> = ObservableField("Title title title title")
    var author: ObservableField<String> = ObservableField("Posted by Ihor Levkivskyi")
    var subreddit_name_prefixed: ObservableField<String> = ObservableField("Sub reddit name")
    var date: ObservableField<String> = ObservableField("11 hours ago")
    var thumbnail: ObservableField<String> = ObservableField("")
    var score: ObservableField<String> = ObservableField("Rating 500k")
    var num_comments: ObservableField<String> = ObservableField("Comment 500k")
    var permalink: ObservableField<String> = ObservableField("")

    private fun getDateTime(time: Long): String? {
        try {
            val netDate = Date(time * 1000)
            return netDate.getTimeAgo()
        } catch (e: Exception) {
            return e.toString()
        }
    }
}