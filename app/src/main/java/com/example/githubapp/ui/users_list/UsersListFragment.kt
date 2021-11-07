package com.example.githubapp.ui.users_list

import android.os.Bundle
import android.view.View
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentEntitiesListBinding
import com.example.githubapp.di.EVENT_BUS_USER
import com.example.githubapp.domain.entities.UserEntity
import com.example.githubapp.event_bus.EventBus
import com.example.githubapp.event_bus.analytic.UserClickEvent
import com.example.githubapp.utils.app
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Named

class UsersListFragment : MvpAppCompatFragment(R.layout.fragment_entities_list),
    UsersListContract.View {

    @InjectPresenter
    lateinit var presenter: UsersListContract.Presenter

    @ProvidePresenter
    fun provide(): UsersListContract.Presenter = app.appComponent.provideUsersListPresenter()

    private val binding: FragmentEntitiesListBinding by viewBinding(FragmentEntitiesListBinding::bind)

    private lateinit var adapter: UsersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = UsersAdapter(this::onUserClicked)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onUserClicked(user: UserEntity) {
        app.appComponent.provideUsersEventBus().postValue(UserClickEvent(user.login))
        presenter.onUserClicked(user)
    }

    override fun setUsers(users: List<UserEntity>) {
        adapter.updateData(users)
    }

    override fun setState(state: UsersListContract.ScreenState) {
        binding.root.children.forEach { it.isVisible = false }

        when (state) {
            UsersListContract.ScreenState.IDLE -> binding.recyclerView.isVisible = true
            UsersListContract.ScreenState.LOADING -> binding.processBar.isVisible = true
            UsersListContract.ScreenState.ERROR -> binding.errorTextView.isVisible = true
        }
    }
}