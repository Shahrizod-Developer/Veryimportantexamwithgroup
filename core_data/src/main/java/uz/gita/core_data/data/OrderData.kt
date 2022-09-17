package uz.gita.core_data

import java.util.*

data class OrderData(
    val id: String = UUID.randomUUID().toString(),
    var name: String,
    var price: Long,
    var description: String,
    var attributes: OrderAttributes
)
