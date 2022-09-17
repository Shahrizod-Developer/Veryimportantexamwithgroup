package uz.gita.veryimportantexamwithgroup.ui.add.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.veryimportantexamwithgroup.data.models.StoreData


interface AddViewModel {
    val messageLiveData: LiveData<String>

    fun addStore(storeData: StoreData)
}