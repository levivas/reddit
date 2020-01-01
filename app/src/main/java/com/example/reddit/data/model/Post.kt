package com.example.reddit.data.model

import com.google.gson.annotations.SerializedName

data class DataPost(
    var kind: String,
    var data: DataChildren)

data class DataChildren(
    var dist: String,
    var children: List<DataChildrenItem>,
    val after: String?,
    val before: String?)

data class DataChildrenItem(var kind: String,
                            var data: Post)

data class Post(
    var title: String,
    var author: String,
    var subreddit_name_prefixed: String,
    @SerializedName("created_utc")
    var postDate: Long,
    var thumbnail: String,
    var num_comments: Int,
    var score: Int,
    val permalink: String)