package com.example.blogapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.blogapp.core.Result
import com.example.blogapp.domain.home.HomeScreenRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class HomeScreenViewModel(private val repo: HomeScreenRepo) : ViewModel() {

    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main

    fun fetchLatestPost() = liveData(viewModelScope.coroutineContext + dispatcherMain) {
        emit(Result.Loading())
        runCatching {
            repo.getLatestPost()
        }.onSuccess { postList ->
            emit(postList)
        }.onFailure { throwable ->
            emit(Result.Failure(Exception(throwable.message)))
        }
    }

    fun registerLikeButtonState(postId: String, liked: Boolean) =
        liveData(viewModelScope.coroutineContext + dispatcherMain) {
            emit(Result.Loading())
            runCatching {
                repo.registerLikeButtonState(postId, liked)
            }.onSuccess {
                emit(Result.Success(Unit))
            }.onFailure { throwable ->
                emit(Result.Failure(Exception(throwable.message)))
            }
        }
}

class HomeScreenViewModelFactory(private val repo: HomeScreenRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeScreenRepo::class.java).newInstance(repo)
    }
}