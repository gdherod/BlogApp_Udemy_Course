package com.example.blogapp.data.remote.home

import com.example.blogapp.core.Result
import com.example.blogapp.data.model.Post
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeScreenDataSource {
    private val dispatcherIo: CoroutineDispatcher = Dispatchers.IO

    suspend fun getLatestPost(): Result<List<Post>> {
        val postList = mutableListOf<Post>()

        withContext(dispatcherIo) {
            val querySnapshot = FirebaseFirestore.getInstance().collection("posts")
                .orderBy("createdAt", Query.Direction.DESCENDING).get().await()

            for (post in querySnapshot.documents) {
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
        }
        return Result.Success(postList)
    }
}