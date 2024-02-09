package com.example.blogapp.domain.home

import com.example.blogapp.core.Result
import com.example.blogapp.data.model.Post
import com.example.blogapp.data.remote.home.HomeScreenDataSource

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource): HomeScreenRepo {
    override suspend fun getLatestPost(): Result<List<Post>> = dataSource.getLatestPost()
}