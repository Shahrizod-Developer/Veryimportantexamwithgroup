package uz.gita.veryimportantexamwithgroup.domain.repositories

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.veryimportantexamwithgroup.data.models.StoreData

interface StoreRepository {

     fun getAllStores(): LiveData<Result<List<StoreData>>>

    fun getAllStores2(): LiveData<Result<List<StoreData>>>

    fun addStore(storeData: StoreData): LiveData<String>

    fun updateStore(storeData: StoreData): LiveData<String>

    fun deleteStore(storeData: StoreData): LiveData<String>
}