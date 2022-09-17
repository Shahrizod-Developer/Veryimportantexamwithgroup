package uz.gita.veryimportantexamwithgroup.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.veryimportantexamwithgroup.navigation.NavigationDispatcher
import uz.gita.veryimportantexamwithgroup.navigation.NavigationHandler
import uz.gita.veryimportantexamwithgroup.navigation.Navigator

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun navigator(dispatcher: NavigationDispatcher): Navigator

    @Binds
    fun navigatorHandler(dispatcher: NavigationDispatcher): NavigationHandler
}