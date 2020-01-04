package com.example.reddit.data.repository

import com.example.reddit.data.model.Listing
import com.example.reddit.data.model.Post

interface RedditPostRepository {
    fun postsReddit(): Listing<Post>
}