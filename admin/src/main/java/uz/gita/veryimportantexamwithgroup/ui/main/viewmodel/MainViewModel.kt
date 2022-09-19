package uz.gita.veryimportantexamwithgroup.ui.main.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.veryimportantexamwithgroup.data.models.StoreData


interface MainViewModel {

    val messageLiveData: LiveData<String>
    val isResume: Flow<Boolean>
    val getData: LiveData<List<StoreData>>
    val progressLiveData: LiveData<Boolean>

    fun deleteStore(storeData: StoreData)

    fun failurMessage(mess: String)

    fun openAdd()
    fun openEdit(storeData: StoreData)
}