package uz.gita.veryimportantexamwithgroup.ui.main.viewmodel.impl

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
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
    override val messageLiveData: MediatorLiveData<String> = MediatorLiveData()
    override val isResume = MutableStateFlow(false)
    override val getData: MutableLiveData<Result<List<StoreData>>> = MutableLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            messageLiveData.addSource(useCase.getAllStores2()) {
                getData.postValue(it)
            }
        }
    }

    override fun deleteStore(storeData: StoreData) {
        messageLiveData.addSource(useCase.deleteStore(storeData)) {
            messageLiveData.postValue(it)
        }
    }

    override fun failurMessage(mess: String) {
        messageLiveData.postValue(mess)
    }

    override fun openAdd() {
        viewModelScope.launch { navigator.navigateTo(MainScreenDirections.actionMainScreenToAddScreen()) }
    }

    override fun openEdit(storeData: StoreData) {
        viewModelScope.launch { navigator.navigateTo(MainScreenDirections.actionMainScreenToEditScreen(storeData)) }
    }

}