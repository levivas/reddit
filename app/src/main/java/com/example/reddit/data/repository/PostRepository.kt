package com.example.reddit.data.repository

import com.example.reddit.data.model.Listing
import com.example.reddit.data.model.Post

interface PostRepository {
    fun postsReddit(pageSize: Int): Listing<Post>
}