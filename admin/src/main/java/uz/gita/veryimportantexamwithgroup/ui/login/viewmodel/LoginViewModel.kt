package uz.gita.veryimportantexamwithgroup.ui.login.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.veryimportantexamwithgroup.data.models.StoreData


interface LoginViewModel {
    val messageLiveData: LiveData<String>
    val isResume: Flow<Boolean>

    fun signIn(login: String, password: String)
}