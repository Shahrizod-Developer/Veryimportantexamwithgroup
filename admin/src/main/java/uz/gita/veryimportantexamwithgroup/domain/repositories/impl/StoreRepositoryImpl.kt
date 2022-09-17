package uz.gita.veryimportantexamwithgroup.domain.repositories.impl

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import uz.gita.veryimportantexamwithgroup.Mapper
import uz.gita.veryimportantexamwithgroup.data.models.StoreData
import uz.gita.veryimportantexamwithgroup.domain.repositories.StoreRepository
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(private val db: CollectionReference) : StoreRepository {
//    private val db = Firebase.firestore

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

    override fun addStore(storeData: StoreData): LiveData<Result<Unit>> {
        val liveData = MutableLiveData<Result<Unit>>()
//        Log.d("TTT", "placingOrder1")
        db.document(storeData.id).set(storeData)
            .addOnCompleteListener {
                Log.d("TTT", "addOnCompleteListener")
            }
            .addOnSuccessListener {
                Log.d("TTT", "addOnSuccessListener")
                liveData.value = Result.success(Unit)
            }
            .addOnFailureListener {
                Log.d("TTT", "addOnFailureListener")
                liveData.value = Result.failure(it)
            }
//        Log.d("TTT", "placingOrder2")
        return liveData
    }

    override fun updateStore(storeData: StoreData): LiveData<String> {
        val liveData = MutableLiveData<String>()
        val docReference: DocumentReference = db.document(storeData.id)
        docReference.update(
            "name", storeData.name,
            "login", storeData.login,
            "password", storeData.password
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                liveData.value = "Update Store"
            } else {
                liveData.value = "Failed"
            }
        }
        return liveData
    }

    override fun deleteStore(storeData: StoreData): LiveData<String> {
        val liveData = MutableLiveData<String>()
        val docReference: DocumentReference = db.document(storeData.id)
        docReference.delete().addOnCompleteListener {
            if (it.isSuccessful) {
                liveData.value = "Delete Store"
            } else {
                liveData.value = "Failed"
            }
        }
        return liveData
    }
}

private fun <T, K> MediatorLiveData<T>.addDisposable(source: LiveData<K>, block: Observer<K>) {
    addSource(source) {
        block.onChanged(it)
        removeSource(source)
    }
}