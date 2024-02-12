package com.example.blogapp.presentation.camera

import android.graphics.Bitmap
import com.example.blogapp.data.remote.camera.CameraDataSource
import com.example.blogapp.domain.camera.CameraRepo

class CameraRepoImpl(private val dataSource: CameraDataSource) : CameraRepo {
    override suspend fun uploadPhoto(imageBitmap: Bitmap, description: String) {
        dataSource.uploadPhoto(imageBitmap, description)
    }
}