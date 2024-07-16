package com.example.emsgtestapp.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.emsgtestapp.databinding.FragmentUserBinding
import com.example.emsgtestapp.model.User
import com.example.emsgtestapp.util.getParcelableCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUserBinding.inflate(layoutInflater)
        val userArg = arguments?.getParcelableCompat<User>("key")
        binding.apply {
            id.text = userArg?.id.toString()
            login.text = userArg?.login
        }

        return binding.root
    }
}