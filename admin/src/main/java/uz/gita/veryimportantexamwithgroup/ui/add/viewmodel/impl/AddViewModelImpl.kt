package uz.gita.veryimportantexamwithgroup.ui.add.viewmodel.impl

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.veryimportantexamwithgroup.data.models.StoreData
import uz.gita.veryimportantexamwithgroup.domain.usecases.StoreUseCase
import uz.gita.veryimportantexamwithgroup.navigation.Navigator
import uz.gita.veryimportantexamwithgroup.ui.add.viewmodel.AddViewModel
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class AddViewModelImpl @Inject constructor(private val useCase: StoreUseCase, private val navigator: Navigator) :
    ViewModel(), AddViewModel {
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

    override fun addStore(storeData: StoreData) {
        if (checkStore(storeData)) {
            messageLiveData.addSource(useCase.addStore(storeData)) {
                messageLiveData.postValue(it)
                add()
            }
        }
    }

    override fun add() {
        viewModelScope.launch { navigator.navigateUp() }
    }

    private fun checkStore(storeData: StoreData): Boolean {
        if (storeData.name.trim().isEmpty() || storeData.login.trim().isEmpty() || storeData.password.trim()
                .isEmpty()
        ) {
            messageLiveData.postValue("Barcha maydonlarni to'ldiring!")
            return false
        } else {
            for (store in storeList) {
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