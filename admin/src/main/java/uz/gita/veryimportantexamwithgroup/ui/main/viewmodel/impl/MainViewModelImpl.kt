package uz.gita.veryimportantexamwithgroup.ui.main.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.veryimportantexamwithgroup.data.models.StoreData
import uz.gita.veryimportantexamwithgroup.domain.usecases.StoreUseCase
import uz.gita.veryimportantexamwithgroup.navigation.Navigator
import uz.gita.veryimportantexamwithgroup.ui.main.MainScreenDirections
import uz.gita.veryimportantexamwithgroup.ui.main.viewmodel.MainViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(private val useCase: StoreUseCase, private val navigator: Navigator) :
    ViewModel(), MainViewModel {
    override val messageLiveData: MutableLiveData<String> = MutableLiveData()
    override val isResume = MutableStateFlow(false)

    override fun getStores(): LiveData<Result<List<StoreData>>> {
        return useCase.getAllStores2()
    }

    override fun deleteStore(storeData: StoreData) {
        useCase.deleteStore(storeData).onEach {
            messageLiveData.value = it
        }
    }

    override fun failurMessage(mess: String) {
        messageLiveData.value = mess
    }

    override fun openAdd() {
        viewModelScope.launch { navigator.navigateTo(MainScreenDirections.actionMainScreenToAddScreen()) }
    }

    override fun openEdit(storeData: StoreData) {
        viewModelScope.launch { navigator.navigateTo(MainScreenDirections.actionMainScreenToEditScreen(storeData)) }
    }

}