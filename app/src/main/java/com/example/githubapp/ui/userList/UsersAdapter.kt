package com.example.githubapp.ui.userList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.databinding.ItemUserBinding
import com.example.githubapp.domain.entities.UserEntity

class UsersAdapter(
    private val onClickListener: (UserEntity) -> Unit
) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    private var data: List<UserEntity> = emptyList()

    fun updateData(newData: List<UserEntity>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(onClickListener, parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class UserViewHolder(
        private val onClickListener: (UserEntity) -> Unit,
        viewGroup: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup, false)
    ) {
        private val binding = ItemUserBinding.bind(itemView)

        fun bind(user: UserEntity) {
            binding.userLoginTextView.text = user.login
            binding.root.setOnClickListener {
                onClickListener(user)
            }
        }
    }
}