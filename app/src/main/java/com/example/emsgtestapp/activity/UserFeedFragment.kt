package com.example.emsgtestapp.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnCloseListener
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.emsgtestapp.R
import com.example.emsgtestapp.adapter.OnInteractionListener
import com.example.emsgtestapp.adapter.UserAdapter
import com.example.emsgtestapp.databinding.FragmentFeedUserBinding
import com.example.emsgtestapp.error.AppError
import com.example.emsgtestapp.model.User
import com.example.emsgtestapp.util.AndroidUtils
import com.example.emsgtestapp.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFeedFragment : Fragment() {
    private val viewModel by viewModels<UsersViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedUserBinding.inflate(layoutInflater)
        val adapter = UserAdapter(object : OnInteractionListener {
            override fun onClick(user: User) {
                binding.search.setQuery("", false)
                findNavController().navigate(
                    R.id.action_userFeedFragment_to_userFragment,
                    bundleOf("key" to user)
                )
            }
        })

        binding.list.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.search.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launch {
                    try {
                        val list = query?.let { viewModel.findUser(it) }
                        binding.apply {
                            adapter.submitList(list)
                        }
                    } catch (e: Exception) {
                        throw AppError.from(e)
                    }
                }
                AndroidUtils.hideKeyboard(requireView())
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        binding.search.setOnCloseListener(object : OnCloseListener {
            override fun onClose(): Boolean {
                return false
            }
        })
        return binding.root
    }
}