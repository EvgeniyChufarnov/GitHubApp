package com.example.githubapp.di

import android.app.Application
import com.example.githubapp.event_bus.EventBus
import com.example.githubapp.event_bus.analytic.AnalyticObserver
import com.example.githubapp.event_bus.analytic.FirebaseAnalyticsApi
import com.example.githubapp.event_bus.analytic.RepoClickEvent
import com.example.githubapp.event_bus.analytic.UserClickEvent
import com.example.githubapp.ui.MainActivity
import com.example.githubapp.ui.repos_list.ReposListContract
import com.example.githubapp.ui.repos_list.ReposListFragment
import com.example.githubapp.ui.users_list.UsersListContract
import com.example.githubapp.ui.users_list.UsersListFragment
import dagger.Binds
import dagger.Component
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Component(
    modules = [CiceroneModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        PresenterModule::class,
        RepoModule::class,
        UtilsModule::class]
)
@Singleton
interface ApplicationComponent {
    fun injectMainActivity(activity: MainActivity)

    fun provideUsersListPresenter(): UsersListContract.Presenter
    fun provideReposListPresenter(): ReposListContract.Presenter

    @Named(EVENT_BUS_USER)
    fun provideUsersEventBus(): EventBus<UserClickEvent>

    @Named(EVENT_BUS_REPO)
    fun provideReposEventBus(): EventBus<RepoClickEvent>
}

class App : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .databaseModule(DatabaseModule(this))
            .utilsModule(UtilsModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        initAnalyticObserver()
    }

    private fun initAnalyticObserver() {
        AnalyticObserver(
            appComponent.provideUsersEventBus(),
            appComponent.provideReposEventBus(),
            FirebaseAnalyticsApi()
        )
    }
}