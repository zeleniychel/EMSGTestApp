package com.example.emsgtestapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.emsgtestapp.databinding.FragmentUserBinding
import com.example.emsgtestapp.domain.error.NetworkError
import com.example.emsgtestapp.data.model.User
import com.example.emsgtestapp.data.util.getParcelableCompat
import com.example.emsgtestapp.presentation.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.IOException

@AndroidEntryPoint
class UserFragment : Fragment() {
    private val viewModel by viewModels<UsersViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUserBinding.inflate(layoutInflater)
        val userArg = arguments?.getParcelableCompat<User>("key")

        lifecycleScope.launch {
            try {
                val user = userArg?.login?.let { viewModel.getUserByLogin(it) }
                binding.apply {
                    id.text = user?.id.toString()
                    login.text = user?.login
                    name.text = user?.name
                    location.text = user?.location
                }
            } catch (e: IOException) {
                throw NetworkError
            } catch (e: Exception) {
                throw UnknownError()
            }
        }

        return binding.root
    }
}