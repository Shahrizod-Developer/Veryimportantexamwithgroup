package uz.gita.veryimportantexamwithgroup.domain.repositories.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.veryimportantexamwithgroup.Mapper
import uz.gita.veryimportantexamwithgroup.data.models.StoreData
import uz.gita.veryimportantexamwithgroup.domain.repositories.StoreRepository
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(private val db: CollectionReference) : StoreRepository {

    private val coroutine = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    override fun getAllStores(): LiveData<Result<List<StoreData>>> {
        val liveData = MutableLiveData<Result<List<StoreData>>>()

        coroutine.launch(Dispatchers.IO) {
            db.get()
                .addOnSuccessListener {
                    val ls = it.documents.map { item -> Mapper.run { item.tostore() } }
                    liveData.postValue(Result.success(ls))
                }
                .addOnFailureListener { liveData.postValue(Result.failure(it)) }
        }

        return liveData
    }

    override fun getAllStores2(): LiveData<Result<List<StoreData>>> {
        val liveData = MediatorLiveData<Result<List<StoreData>>>()
        liveData.addDisposable(getAllStores()) { liveData.postValue(it) }

        db.addSnapshotListener { snapshot, e ->
            liveData.addDisposable(
                getAllStores()
            ) { liveData.postValue(it) }
        }

        return liveData
    }

    override fun addStore(storeData: StoreData): LiveData<String> {
        val messageLiveData = MutableLiveData<String>()
        db.document(storeData.id).set(storeData)
            .addOnCompleteListener {
                messageLiveData.postValue("Add complete")
            }
            .addOnSuccessListener {
                messageLiveData.postValue("Add success")
            }
            .addOnFailureListener {
                messageLiveData.postValue("Add failure")
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
            messageLiveData.postValue("Add complete")
        }
            .addOnSuccessListener {
                messageLiveData.postValue("Add success")
            }
            .addOnFailureListener {
                messageLiveData.postValue("Add failure")
            }
        return messageLiveData
    }

    override fun deleteStore(storeData: StoreData): LiveData<String> {
        val messageLiveData = MutableLiveData<String>()
        db.document(storeData.id).delete().addOnCompleteListener {
            messageLiveData.postValue("Delete complete")
        }
            .addOnSuccessListener {
                messageLiveData.postValue("Delete success")
            }
            .addOnFailureListener {
                messageLiveData.postValue("Delete failure")
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