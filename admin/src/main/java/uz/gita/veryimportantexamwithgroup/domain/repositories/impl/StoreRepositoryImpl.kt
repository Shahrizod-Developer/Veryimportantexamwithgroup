package uz.gita.veryimportantexamwithgroup.domain.repositories.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.veryimportantexamwithgroup.Mapper
import uz.gita.veryimportantexamwithgroup.Mapper.toStore
import uz.gita.veryimportantexamwithgroup.data.models.StoreData
import uz.gita.veryimportantexamwithgroup.domain.repositories.StoreRepository
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(private val db: CollectionReference) : StoreRepository {

    private val coroutine = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

//    override fun getAllStores(): LiveData<Result<List<StoreData>>> {
//        val liveData = MutableLiveData<Result<List<StoreData>>>()
//
//        coroutine.launch(Dispatchers.IO) {
//            db.get()
//                .addOnSuccessListener {
//                    val ls = it.documents.map { item -> Mapper.run { item.toStore() } }
//                    liveData.postValue(Result.success(ls))
//                }
//                .addOnFailureListener { liveData.postValue(Result.failure(it)) }
//        }
//
//        return liveData
//    }

    override fun getStores(): Flow<List<StoreData>> = callbackFlow {
        val subscriber = db.addSnapshotListener { value, error ->
            val list = value?.documents?.map {
                it.toStore()
            }
            trySend(list ?: emptyList())
        }
        awaitClose {
            subscriber.remove()
        }
    }

//    override fun getAllStores2(): LiveData<Result<List<StoreData>>> {
//        val liveData = MediatorLiveData<Result<List<StoreData>>>()
//        liveData.addDisposable(getAllStores()) { liveData.postValue(it) }
//
//        db.addSnapshotListener { snapshot, e ->
//            liveData.addDisposable(
//                getAllStores()
//            ) { liveData.postValue(it) }
//        }
//
//        return liveData
//    }

    override fun addStore(storeData: StoreData): LiveData<String> {
        val messageLiveData = MutableLiveData<String>()
        db.document(storeData.id).set(storeData)
            .addOnCompleteListener {
                messageLiveData.postValue("Do'kon qo'shilmoqda")
            }
            .addOnSuccessListener {
                messageLiveData.postValue("Do'kon ma'lumotlari qo'shildi")
            }
            .addOnFailureListener {
                messageLiveData.postValue("Qo'shishda xatolik yuz berdi")
            }
        return messageLiveData
    }

    override fun updateStore(storeData: StoreData): LiveData<String> {
        val messageLiveData = MutableLiveData<String>()
        db.document(storeData.id).update(
            "name", storeData.name,
            "login", storeData.login,
            "password", storeData.password
        ).addOnCompleteListener {
            messageLiveData.postValue("Do'kon o'zgartirilmoqda")
        }
            .addOnSuccessListener {
                messageLiveData.postValue("Do'kon ma'lumotlari o'zgartirildi")
            }
            .addOnFailureListener {
                messageLiveData.postValue("O'zgartirishda xatolik yuz berdi!")
            }
        return messageLiveData
    }

    override fun deleteStore(storeData: StoreData): LiveData<String> {
        val messageLiveData = MutableLiveData<String>()
        db.document(storeData.id).delete().addOnCompleteListener {
            messageLiveData.postValue("Do'kon o'chirilmoqda")
        }
            .addOnSuccessListener {
                messageLiveData.postValue("Do'kon ma'lumotlari o'chirildi")
            }
            .addOnFailureListener {
                messageLiveData.postValue("O'chirishda xatolik yuz berdi!")
            }
        return messageLiveData
    }
}

private fun <T, K> MediatorLiveData<T>.addDisposable(source: LiveData<K>, block: Observer<K>) {
    addSource(source) {
        block.onChanged(it)
        removeSource(source)
    }
}
