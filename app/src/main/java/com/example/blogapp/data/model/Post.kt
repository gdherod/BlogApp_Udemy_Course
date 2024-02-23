package com.example.blogapp.data.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Post(
    @Exclude @JvmField
    val id: String = "",
    @ServerTimestamp
    var createdAt: Date? = null,
    val postImage: String = "",
    val postDescription: String = "",
    val poster: Poster? = null,
    val likes: Long = 0,
    @Exclude @JvmField
    var liked: Boolean = false
)

data class Poster(
    val username: String? = "",
    val profilePicture: String = "",
    val uid: String? = null
)