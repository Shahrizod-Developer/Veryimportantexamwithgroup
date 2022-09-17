package uz.gita.veryimportantexamwithgroup.domain.repositories.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.veryimportantexamwithgroup.Mapper
import uz.gita.veryimportantexamwithgroup.data.models.StoreData
import uz.gita.veryimportantexamwithgroup.domain.repositories.StoreRepository
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(private val db: CollectionReference) : StoreRepository {

    override fun getAllStores(): LiveData<Result<List<StoreData>>> {
        val liveData = MutableLiveData<Result<List<StoreData>>>()

        db.get()
            .addOnSuccessListener {
                val ls = it.documents.map { item -> Mapper.run { item.tostore() } }
                liveData.value = Result.success(ls)
            }
            .addOnFailureListener { liveData.value = Result.failure(it) }

        return liveData
    }

    override fun getAllStores2(): LiveData<Result<List<StoreData>>> {
        val liveData = MediatorLiveData<Result<List<StoreData>>>()
        liveData.addDisposable(getAllStores()) { liveData.value = it }

        db.addSnapshotListener { snapshot, e ->
            liveData.addDisposable(getAllStores()) { liveData.value = it }
        }

        return liveData
    }

    override fun addStore(storeData: StoreData): Flow<String> = flow {
        var message = ""
        db.document(storeData.id).set(storeData)
            .addOnCompleteListener {
                message = "Add complete"
            }
            .addOnSuccessListener {
                Log.d("TTT", "addOnSuccessListener")
                message = "Add success"
            }
            .addOnFailureListener {
                Log.d("TTT", "addOnFailureListener")
                message = "Add failure"
            }
        emit(message)
    }

    override fun updateStore(storeData: StoreData): Flow<String> = flow {
        var message = ""
        val docReference: DocumentReference = db.document(storeData.id)
        docReference.update(
            "name", storeData.name,
            "login", storeData.login,
            "password", storeData.password
        ).addOnCompleteListener {
            message = if (it.isSuccessful) {
                "Update Store"
            } else {
                "Failed"
            }
        }
        emit(message)
    }

    override fun deleteStore(storeData: StoreData): Flow<String> = flow {
        var message = ""
        val docReference: DocumentReference = db.document(storeData.id)
        docReference.delete().addOnCompleteListener {
            if (it.isSuccessful) {
                message = "Delete Store"
            } else {
                message = "Failed"
            }
        }
        emit(message)
    }
}

private fun <T, K> MediatorLiveData<T>.addDisposable(source: LiveData<K>, block: Observer<K>) {
    addSource(source) {
        block.onChanged(it)
        removeSource(source)
    }
}