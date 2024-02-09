package com.example.blogapp.domain.auth

import com.example.blogapp.data.remote.auth.LoginDataSource
import com.google.firebase.auth.FirebaseUser

class LoginRepoImpl(private val dataSource: LoginDataSource) : LoginRepo {
    override suspend fun singIn(email: String, password: String): FirebaseUser? =
        dataSource.signIn(email, password)
}