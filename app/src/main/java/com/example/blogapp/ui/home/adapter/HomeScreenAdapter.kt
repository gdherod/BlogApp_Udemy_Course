package com.example.blogapp.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blogapp.R
import com.example.blogapp.core.BaseViewHolder
import com.example.blogapp.core.TimeUtils
import com.example.blogapp.core.hide
import com.example.blogapp.core.show
import com.example.blogapp.data.model.Post
import com.example.blogapp.databinding.PostItemViewBinding

class HomeScreenAdapter(
    private val postList: List<Post>,
    private val onPostClickListener: OnPostClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    private var postClickListener: OnPostClickListener? = null

    init {
        postClickListener = onPostClickListener
    }

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
            tintHeartIcon(item)
            setupLikeCount(item)
            setLikeClickAction(item)
        }

        private fun setupProfileInfo(post: Post) {
            Glide.with(context).load(post.poster?.profilePicture).centerCrop()
                .into(binding.profilePicture)
            binding.profileName.text = post.poster?.username
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

        private fun tintHeartIcon(post: Post) {
            if (!post.liked) {
                binding.btnLike.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.baseline_favorite_border_24
                    )
                )
                binding.btnLike.setColorFilter(ContextCompat.getColor(context, R.color.black))
            } else {
                binding.btnLike.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.baseline_favorite_24
                    )
                )
                binding.btnLike.setColorFilter(ContextCompat.getColor(context, R.color.liked_post))
            }
        }

        private fun setupLikeCount(post: Post) {
            if (post.likes > 0) {
                binding.txtLikeCount.show()
                binding.txtLikeCount.text = "${post.likes} likes"
            } else {
                binding.txtLikeCount.hide()
            }
        }

        private fun setLikeClickAction(post: Post) {
            binding.btnLike.setOnClickListener {
                if (post.liked) post.apply { liked = false } else post.apply { liked = true }
                tintHeartIcon(post)
                postClickListener?.onLikeButtonClick(post, post.liked)
            }
        }
    }
}

interface OnPostClickListener {
    fun onLikeButtonClick(post: Post, liked: Boolean)
}