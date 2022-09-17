package uz.gita.veryimportantexamwithgroup.ui.add.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.veryimportantexamwithgroup.data.models.StoreData
import uz.gita.veryimportantexamwithgroup.domain.usecases.StoreUseCase
import uz.gita.veryimportantexamwithgroup.ui.add.viewmodel.AddViewModel
import javax.inject.Inject

class AddViewModelImpl @Inject constructor(private val useCase: StoreUseCase) : ViewModel(), AddViewModel {
    override val messageLiveData: MutableLiveData<String> = MutableLiveData()

    override fun addStore(storeData: StoreData) {
        if (checkStore(storeData)) {
            useCase.addStore(storeData)
                .onEach {
                    messageLiveData.value = it
                }.launchIn(viewModelScope)
        }
    }

    private fun checkStore(storeData: StoreData): Boolean {
        if (storeData.name.trim().isEmpty() && storeData.login.trim().isEmpty() && storeData.password.trim().isEmpty()) {
            messageLiveData.value = "Fill in all fields!"
            return false
        } else {
            return true
        }
    }
}