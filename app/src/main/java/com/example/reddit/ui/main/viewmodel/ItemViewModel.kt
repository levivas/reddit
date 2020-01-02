package com.example.reddit.ui.main.viewmodel

import androidx.databinding.ObservableField
import com.example.reddit.data.model.Post
import com.example.reddit.ui.base.BaseViewModel
import java.util.*

class ItemViewModel : BaseViewModel() {

    fun initPost(post: Post) {
        title.set(post.title)
        author.set("Posted by " + post.author)
        subreddit_name_prefixed.set(post.subreddit_name_prefixed)
        date.set(getDateTime(post.postDate))
        thumbnail.set(post.thumbnail)
//        thumbnail.set("https://external-preview.redd.it/upjwqZhv4enzkZzaov9l0KRYbRaTSIJW_aU2r1XDZL8.jpg")
        score.set(post.score.toString())
        num_comments.set(post.num_comments.toString())
        permalink.set(post.permalink)
    }
    //    val finishSelection = SingleLiveEvent<Unit>()
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
            return getTimeAgo(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

    private val SECOND_MILLIS = 1000
    private val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private val DAY_MILLIS = 24 * HOUR_MILLIS

    private fun currentDate(): Date {
        val calendar = Calendar.getInstance()
        return calendar.time
    }

    fun getTimeAgo(date: Date): String {
        var time = date.time
        if (time < 1000000000000L) {
            time *= 1000
        }

        val now = currentDate().time
        if (time > now || time <= 0) {
            return "in the future"
        }

        val diff = now - time
        return when {
            diff < MINUTE_MILLIS -> "moments ago"
            diff < 2 * MINUTE_MILLIS -> "a minute ago"
            diff < 60 * MINUTE_MILLIS -> "${diff / MINUTE_MILLIS} minutes ago"
            diff < 2 * HOUR_MILLIS -> "an hour ago"
            diff < 24 * HOUR_MILLIS -> "${diff / HOUR_MILLIS} hours ago"
            diff < 48 * HOUR_MILLIS -> "yesterday"
            else -> "${diff / DAY_MILLIS} days ago"
        }
    }
}