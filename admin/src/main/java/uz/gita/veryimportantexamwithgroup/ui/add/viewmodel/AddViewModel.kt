package uz.gita.veryimportantexamwithgroup.ui.add.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.veryimportantexamwithgroup.data.models.StoreData


interface AddViewModel {
    val messageLiveData: LiveData<String>
    val isResume: Flow<Boolean>

    fun addStore(storeData: StoreData)

    fun add()
}