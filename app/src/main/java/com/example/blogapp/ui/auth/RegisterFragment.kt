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

            if (validateUserData(
                    username,
                    email,
                    password,
                    confirmPassword
                )
            ) return@setOnClickListener

            Log.d("signUpDate", "data: $username $email $password $confirmPassword")
        }
    }

    private fun validateUserData(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (password != confirmPassword) {
            binding.editTextConfirmPassword.error = "Password does not match"
            binding.editTextPassword.error = "Password does not match"
            return true
        }

        when {
            username.isEmpty() -> {
                binding.editTextUserName.error = "UserName is empty"
                return true
            }

            email.isEmpty() -> {
                binding.editTextEmail.error = "E-mail is empty"
                return true
            }

            password.isEmpty() -> {
                binding.editTextPassword.error = "Password is empty"
                return true
            }

            confirmPassword.isEmpty() -> {
                binding.editTextConfirmPassword.error = "Confirm password is empty"
                return true
            }
        }
        return false
    }
}