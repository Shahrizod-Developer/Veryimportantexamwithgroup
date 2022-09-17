package uz.gita.veryimportantexamwithgroup.ui.edit.viewmodel.impl

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
import uz.gita.veryimportantexamwithgroup.ui.add.viewmodel.AddViewModel
import uz.gita.veryimportantexamwithgroup.ui.edit.viewmodel.EditViewModel
import javax.inject.Inject

@HiltViewModel
class EditViewModelImpl @Inject constructor(private val useCase: StoreUseCase, private val navigator: Navigator) : ViewModel(), EditViewModel {
    override val messageLiveData: MutableLiveData<String> = MutableLiveData()
    override val isResume = MutableStateFlow(false)

    override fun updateStore(storeData: StoreData) {
        if (checkStore(storeData)) {
            useCase.updateStore(storeData)
                .onEach {
                    messageLiveData.value = it
                    navigateUp()
                }.launchIn(viewModelScope)
        }
    }

    override fun navigateUp() {
        viewModelScope.launch { navigator.navigateUp() }
    }

    private fun checkStore(storeData: StoreData): Boolean {
        return if (storeData.name.trim().isEmpty() && storeData.login.trim().isEmpty() && storeData.password.trim().isEmpty()) {
            messageLiveData.value = "Fill in all fields!"
            false
        } else {
            true
        }
    }
}