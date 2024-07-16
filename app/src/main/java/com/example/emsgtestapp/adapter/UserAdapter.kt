package com.example.emsgtestapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.emsgtestapp.databinding.CardUserBinding
import com.example.emsgtestapp.model.User


interface OnInteractionListener {
    fun onClick(user: User) {}
}

class UserAdapter(
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<User, UserViewHolder>(UserDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = CardUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

}

class UserViewHolder(
    private val binding: CardUserBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.apply {
            id.text = user.id.toString()
            login.text = user.login
            root.setOnClickListener {
                onInteractionListener.onClick(user)
            }
        }
    }
}

class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}