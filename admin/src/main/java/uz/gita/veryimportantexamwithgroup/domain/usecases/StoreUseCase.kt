package uz.gita.veryimportantexamwithgroup.domain.usecases

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.veryimportantexamwithgroup.data.models.StoreData

interface StoreUseCase {

//    fun getAllStores2(): LiveData<Result<List<StoreData>>>
    fun getStores(): Flow<List<StoreData>>

    fun addStore(storeData: StoreData): LiveData<String>

    fun updateStore(storeData: StoreData): LiveData<String>

    fun deleteStore(storeData: StoreData): LiveData<String>
}