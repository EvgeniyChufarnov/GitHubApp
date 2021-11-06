package com.example.githubapp.ui.repos_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.databinding.ItemRepoBinding
import com.example.githubapp.domain.entities.RepoEntity

class ReposAdapter(
    private val onClickListener: (RepoEntity) -> Unit
) : RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {
    private var data: List<RepoEntity> = emptyList()

    fun updateData(newData: List<RepoEntity>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(onClickListener, parent)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class RepoViewHolder(
        private val onClickListener: (RepoEntity) -> Unit,
        viewGroup: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(R.layout.item_repo, viewGroup, false)
    ) {
        private val binding = ItemRepoBinding.bind(itemView)

        fun bind(repo: RepoEntity) {
            binding.repoLoginTextView.text = repo.name
            binding.root.setOnClickListener {
                onClickListener(repo)
            }
        }
    }
}