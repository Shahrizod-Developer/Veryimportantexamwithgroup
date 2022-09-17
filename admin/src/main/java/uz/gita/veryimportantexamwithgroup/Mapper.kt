package uz.gita.veryimportantexamwithgroup

import com.google.firebase.firestore.DocumentSnapshot
import uz.gita.veryimportantexamwithgroup.data.models.StoreData

object Mapper {
    fun DocumentSnapshot.tostore() = StoreData(
        id = this["id"].toString(),
        login = this["login"].toString(),
        name = this["name"].toString(),
        password = this["password"].toString()
    )
}