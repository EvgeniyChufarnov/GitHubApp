package com.example.githubapp.ui.repo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentRepoBinding
import com.example.githubapp.domain.entities.RepoEntity

class RepoFragment : Fragment(R.layout.fragment_repo) {
    private val binding: FragmentRepoBinding by viewBinding(FragmentRepoBinding::bind)

    companion object {
        private const val REPO_KEY = "repo"

        fun getInstance(repo: RepoEntity) = RepoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPO_KEY, repo)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<RepoEntity>(REPO_KEY)?.let {
            binding.repoNameTextView.text = it.name
            binding.repoForksNumTextView.text = getString(R.string.num_of_forks, it.forks)
        }
    }
}