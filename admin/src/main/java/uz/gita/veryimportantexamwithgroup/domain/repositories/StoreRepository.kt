package uz.gita.veryimportantexamwithgroup.domain.repositories

import androidx.lifecycle.LiveData
import uz.gita.veryimportantexamwithgroup.data.models.StoreData

interface StoreRepository {

    fun getAllStores(): LiveData<Result<List<StoreData>>>

    fun getAllStores2(): LiveData<Result<List<StoreData>>>

    fun addStore(storeData: StoreData): LiveData<Result<Unit>>

    fun updateStore(storeData: StoreData): LiveData<String>

    fun deleteStore(storeData: StoreData): LiveData<String>
}