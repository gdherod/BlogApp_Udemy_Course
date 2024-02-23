package com.example.blogapp.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blogapp.core.BaseViewHolder
import com.example.blogapp.core.TimeUtils
import com.example.blogapp.core.hide
import com.example.blogapp.data.model.Post
import com.example.blogapp.databinding.PostItemViewBinding

class HomeScreenAdapter(private val postList: List<Post>) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            PostItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeScreenViewHolder(itemBinding, parent.context)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is HomeScreenViewHolder -> holder.bind(postList[position])
        }
    }

    private inner class HomeScreenViewHolder(
        val binding: PostItemViewBinding,
        val context: Context
    ) : BaseViewHolder<Post>(binding.root) {
        override fun bind(item: Post) {
            setupProfileInfo(item)
            addPostTimeStamp(item)
            setupPostImage(item)
            setupPostDescription(item)
        }

        private fun setupProfileInfo(post: Post) {
            Glide.with(context).load(post.profilePicture).centerCrop().into(binding.profilePicture)
            binding.profileName.text = post.profileName
        }

        private fun addPostTimeStamp(post: Post) {
            val createdAt = (post.createdAt?.time?.div(1000L))?.let {
                TimeUtils.getTimeAgo(it.toInt())
            }
            binding.postTimestamp.text = createdAt
        }

        private fun setupPostImage(post: Post) {
            Glide.with(context).load(post.postImage).centerCrop().into(binding.postImage)
        }

        private fun setupPostDescription(post: Post) {
            if (post.postDescription.isEmpty()) {
                binding.postDescription.hide()
            } else {
                binding.postDescription.text = post.postDescription
            }
        }
    }
}