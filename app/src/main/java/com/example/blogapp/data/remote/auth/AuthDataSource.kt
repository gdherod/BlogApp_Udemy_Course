package com.example.blogapp.data.remote.auth

import android.graphics.Bitmap
import com.example.blogapp.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthDataSource {
    suspend fun signIn(email: String, password: String): FirebaseUser? {
        val authResult =
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
        return authResult.user
    }

    suspend fun signUp(username: String, password: String, email: String): FirebaseUser? {
        val authResult =
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
        authResult.user?.uid?.let { uid ->
            FirebaseFirestore.getInstance().collection("users").document(uid)
                .set(User(username, email, "PHOTO_URL.PNG")).await()
        }
        return authResult.user
    }

    suspend fun updateUserProfile(imageBitmap: Bitmap, username: String) {

    }
}