package com.example.blogapp.domain.auth

import android.graphics.Bitmap
import com.example.blogapp.data.remote.auth.AuthDataSource
import com.google.firebase.auth.FirebaseUser

class AuthRepoImpl(private val dataSource: AuthDataSource) : AuthRepo {
    override suspend fun singIn(email: String, password: String): FirebaseUser? =
        dataSource.signIn(email, password)

    override suspend fun singUp(username: String, email: String, password: String): FirebaseUser? =
        dataSource.signUp(username, password, email)

    override suspend fun updateProfile(imageBitmap: Bitmap, username: String) =
        dataSource.updateUserProfile(imageBitmap, username)
}