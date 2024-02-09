package com.example.blogapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.blogapp.R
import com.example.blogapp.data.model.Post
import com.example.blogapp.databinding.FragmentHomeScreenBinding
import com.example.blogapp.ui.home.adapter.HomeScreenAdapter
import com.google.firebase.Timestamp

class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)

        val postList = listOf(
            Post("profile", "User 1", Timestamp.now(), "urlImage"),
            Post("profile", "User 2", Timestamp.now(), "urlImage"),
            Post("profile", "User 3", Timestamp.now(), "urlImage")
        )

        binding.rvHome.adapter = HomeScreenAdapter(postList)
    }

}