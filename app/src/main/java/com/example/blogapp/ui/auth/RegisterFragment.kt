package com.example.blogapp.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.blogapp.R
import com.example.blogapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        SignUp()
    }

    private fun SignUp() {
        binding.btnSignup.setOnClickListener {
            val username = binding.editTextUserName.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()

            if (password != confirmPassword) {
                binding.editTextConfirmPassword.error = "Password does not match"
                binding.editTextPassword.error = "Password does not match"
                return@setOnClickListener
            }

            when {
                username.isEmpty() -> {
                    binding.editTextUserName.error = "UserName is empty"
                    return@setOnClickListener
                }
                email.isEmpty() -> {
                    binding.editTextEmail.error = "E-mail is empty"
                    return@setOnClickListener
                }
                password.isEmpty() -> {
                    binding.editTextPassword.error = "Password is empty"
                    return@setOnClickListener
                }
                confirmPassword.isEmpty() -> {
                    binding.editTextConfirmPassword.error = "Confirm password is empty"
                    return@setOnClickListener
                }
            }

            Log.d("signUpDate", "data: $username $email $password $confirmPassword")
        }
    }
}