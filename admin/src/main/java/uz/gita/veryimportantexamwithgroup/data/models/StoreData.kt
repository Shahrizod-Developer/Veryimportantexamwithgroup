package uz.gita.veryimportantexamwithgroup.data.models

import java.io.Serializable
import java.util.*

data class StoreData(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val login: String,
    val password: String
): Serializable