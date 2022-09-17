package uz.gita.veryimportantexamwithgroup.ui.edit.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.veryimportantexamwithgroup.data.models.StoreData


interface EditViewModel {
    val messageLiveData: LiveData<String>
    val isResume: Flow<Boolean>

    fun updateStore(storeData: StoreData)
}