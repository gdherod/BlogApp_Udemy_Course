package com.example.blogapp.data.remote.home

import com.example.blogapp.core.Result
import com.example.blogapp.data.model.Post
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class HomeScreenDataSource {

    suspend fun getLatestPost(): Flow<Result<List<Post>>> = callbackFlow {
        val postList = mutableListOf<Post>()
        var postReference: Query? = null

        try {
            postReference = FirebaseFirestore.getInstance().collection("posts")
                .orderBy("createdAt", Query.Direction.DESCENDING)
        } catch (e: Throwable) {
            close(e)
        }

        val subscription = postReference?.addSnapshotListener { value, error ->
            if (value == null) return@addSnapshotListener
            try {
                postList.clear()
                for (post in value.documents) {
                    post.toObject(Post::class.java)?.let { fbPost ->
                        fbPost.apply {
                            createdAt = post.getTimestamp(
                                "createdAt",
                                DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
                            )?.toDate()
                        }
                        postList.add(fbPost)
                    }
                }
            } catch (e: Exception) {
                close(e)
            }
            trySend(Result.Success(postList)).isSuccess
        }
        awaitClose { subscription?.remove() }
    }
}