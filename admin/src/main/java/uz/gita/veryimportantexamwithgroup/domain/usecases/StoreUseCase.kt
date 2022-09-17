package uz.gita.veryimportantexamwithgroup.domain.usecases

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.veryimportantexamwithgroup.data.models.StoreData

interface StoreUseCase {

    fun getAllStores2(): LiveData<Result<List<StoreData>>>

    fun addStore(storeData: StoreData): Flow<String>

    fun updateStore(storeData: StoreData): Flow<String>

    fun deleteStore(storeData: StoreData): Flow<String>
}