package uz.gita.veryimportantexamwithgroup.ui.login.viewmodel.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.veryimportantexamwithgroup.data.models.StoreData
import uz.gita.veryimportantexamwithgroup.domain.usecases.StoreUseCase
import uz.gita.veryimportantexamwithgroup.navigation.Navigator
import uz.gita.veryimportantexamwithgroup.ui.login.LoginScreenDirections
import uz.gita.veryimportantexamwithgroup.ui.login.viewmodel.LoginViewModel
import uz.gita.veryimportantexamwithgroup.ui.main.MainScreenDirections
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(private val navigator: Navigator) : ViewModel(), LoginViewModel {
    override val messageLiveData: MutableLiveData<String> = MutableLiveData()
    override val isResume = MutableStateFlow(false)

    override fun signIn(login: String, password: String) {
        if (login.trim().isEmpty() || password.trim().isEmpty()) {
            messageLiveData.value = "Fill in all fields"
        } else {
            if (login == "admin" && password == "12345") {
                viewModelScope.launch { navigator.navigateTo(LoginScreenDirections.actionLoginScreenToMainScreen()) }
            } else {
                messageLiveData.value = "Incorrect login or password. Please check and re-enter"
            }
        }
    }

}