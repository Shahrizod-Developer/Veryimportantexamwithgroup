package uz.gita.veryimportantexamwithgroup.domain.usecases.impl

import androidx.lifecycle.LiveData
import uz.gita.veryimportantexamwithgroup.data.models.StoreData
import uz.gita.veryimportantexamwithgroup.domain.repositories.StoreRepository
import uz.gita.veryimportantexamwithgroup.domain.usecases.StoreUseCase
import javax.inject.Inject

class StoreUseCaseImpl @Inject constructor(private val storeRepository: StoreRepository) : StoreUseCase {

    override fun getAllStores2(): LiveData<Result<List<StoreData>>> = storeRepository.getAllStores2()

    override fun addStore(storeData: StoreData): LiveData<Result<Unit>> = storeRepository.addStore(storeData)

    override fun updateStore(storeData: StoreData): LiveData<String> = storeRepository.updateStore(storeData)

    override fun deleteStore(storeData: StoreData): LiveData<String> = storeRepository.deleteStore(storeData)
}