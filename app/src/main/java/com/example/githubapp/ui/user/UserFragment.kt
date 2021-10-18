package com.example.githubapp.ui.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentUserBinding

class UserFragment : Fragment(R.layout.fragment_user) {
    private val binding: FragmentUserBinding by viewBinding(FragmentUserBinding::bind)

    companion object {
        private const val LOGIN_KEY = "login"

        fun getInstance(roverName: String) = UserFragment().apply {
            arguments = Bundle().apply {
                putString(LOGIN_KEY, roverName)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val login = arguments?.getString(LOGIN_KEY) ?: getString(R.string.unknown_user)
        binding.loginTextView.text = login
    }
}