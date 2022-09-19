package uz.gita.veryimportantexamwithgroup.ui.edit.viewmodel.impl

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.veryimportantexamwithgroup.data.models.StoreData
import uz.gita.veryimportantexamwithgroup.domain.usecases.StoreUseCase
import uz.gita.veryimportantexamwithgroup.navigation.Navigator
import uz.gita.veryimportantexamwithgroup.ui.add.viewmodel.AddViewModel
import uz.gita.veryimportantexamwithgroup.ui.edit.viewmodel.EditViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EditViewModelImpl @Inject constructor(private val useCase: StoreUseCase, private val navigator: Navigator) :
    ViewModel(), EditViewModel {
    override val messageLiveData: MediatorLiveData<String> = MediatorLiveData()
    override val isResume = MutableStateFlow(false)
    private var storeList: ArrayList<StoreData> = ArrayList()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getStores().collect {
                storeList.clear()
                storeList.addAll(it)
            }
        }
    }

    override fun updateStore(storeData: StoreData) {
        if (checkStore(storeData)) {
            messageLiveData.addSource(useCase.updateStore(storeData)) {
                messageLiveData.postValue(it)
                navigateUp()
            }
        }
    }

    override fun navigateUp() {
        viewModelScope.launch { navigator.navigateUp() }
    }

    private fun checkStore(storeData: StoreData): Boolean {
        if (storeData.name.trim().isEmpty() || storeData.login.trim().isEmpty() || storeData.password.trim()
                .isEmpty()
        ) {
            messageLiveData.value = "Barcha maydonlarni to'ldiring!"
            return false
        } else {
            for (store in storeList) {
                if (store.id == storeData.id) continue
                if (storeData.name.lowercase(Locale.ROOT) == store.name.lowercase(Locale.ROOT)) {
                    messageLiveData.postValue("Bu nomdagi do'kon mavjud!")
                    return false
                }
                if (storeData.login.lowercase(Locale.ROOT) == store.login.lowercase(Locale.ROOT)) {
                    messageLiveData.postValue("Bunday login mavjud. Boshqa login kiriting!")
                    return false
                }
            }
            return true
        }
    }
}