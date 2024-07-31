package com.example.emsgtestapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.emsgtestapp.R
import com.example.emsgtestapp.presentation.adapter.OnInteractionListener
import com.example.emsgtestapp.presentation.adapter.UserAdapter
import com.example.emsgtestapp.databinding.FragmentFeedUserBinding
import com.example.emsgtestapp.data.model.User
import com.example.emsgtestapp.presentation.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFeedFragment : Fragment() {
    private lateinit var binding: FragmentFeedUserBinding
    private lateinit var adapter: UserAdapter
    private val viewModel by viewModels<UsersViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedUserBinding.inflate(layoutInflater)
        adapter = UserAdapter(object : OnInteractionListener {
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.search.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.findUser(it) }
                return true
            }
        })
    }
}