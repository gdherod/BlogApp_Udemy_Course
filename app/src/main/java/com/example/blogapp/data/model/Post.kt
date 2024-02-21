package com.example.blogapp.data.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Post(
    val profilePicture: String = "",
    val profileName: String = "",
    @ServerTimestamp
    var createdAt: Date? = null,
    val postImage: String = "",
    val postDescription: String = "",
    val uid: String = ""
)
