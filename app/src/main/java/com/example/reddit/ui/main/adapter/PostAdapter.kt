package com.example.reddit.ui.main.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.reddit.data.model.Post
import com.example.reddit.databinding.RowLayoutBinding
import com.example.reddit.ui.base.BaseViewHolder
import com.example.reddit.ui.main.viewmodel.ItemViewModel
import com.example.reddit.utils.AppConstants
import com.example.reddit.utils.CustomTabHelper
import kotlinx.android.synthetic.main.row_layout.view.*

class PostAdapter : PagedListAdapter<Post, BaseViewHolder<Post>>(POST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Post> {
        return PostViewHolder(
            RowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Post>, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class PostViewHolder constructor(val binding: RowLayoutBinding) :
        BaseViewHolder<Post>(binding.root) {
        val viewModel = ItemViewModel()

        init {
            binding.root.main_container.setOnClickListener {
                openDetail(viewModel.permalink.get()!!, binding.root.context)
            }
            binding.viewModel = viewModel
        }

        override fun bind(data: Post) {
            if (data.thumbnail.startsWith("https")) {
                binding.imageThumbnail.visibility = View.VISIBLE
            } else {
                binding.imageThumbnail.visibility = View.GONE
            }
            viewModel.initPost(data)
        }

        private fun openDetail(url: String, context: Context) {
            val builder = CustomTabsIntent.Builder()
            builder.addDefaultShareMenuItem()
            builder.setShowTitle(true)
            builder.setStartAnimations(context, android.R.anim.fade_in, android.R.anim.fade_out)
            builder.setExitAnimations(context, android.R.anim.fade_in, android.R.anim.fade_out)
            val packageName = CustomTabHelper.getPackageNameToUse(
                context,
                AppConstants.BASE_URL.dropLast(1) + url
            )
            val customTabsIntent = builder.build()
            customTabsIntent.intent.setPackage(packageName)
            customTabsIntent.launchUrl(context, Uri.parse(AppConstants.BASE_URL.dropLast(1) + url))
        }
    }

    companion object {
        private val PAYLOAD_SCORE = Any()
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<Post>() {
            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem.title == newItem.title

            override fun getChangePayload(oldItem: Post, newItem: Post): Any? {
                return if (sameExceptScore(oldItem, newItem)) {
                    PAYLOAD_SCORE
                } else {
                    null
                }
            }

            private fun sameExceptScore(oldItem: Post, newItem: Post): Boolean {
                return oldItem.copy(score = newItem.score) == newItem
            }
        }
    }
}