package uz.gita.veryimportantexamwithgroup.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.veryimportantexamwithgroup.domain.repositories.StoreRepository
import uz.gita.veryimportantexamwithgroup.domain.repositories.impl.StoreRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bind(impl: StoreRepositoryImpl): StoreRepository
}