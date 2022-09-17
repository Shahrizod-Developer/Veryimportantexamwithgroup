package uz.gita.core_data.data.local

// Created by Jamshid Isoqov an 9/17/2022
data class OrdersData(
    val id: String,
    val clientId: String,
    val status: String,
    val details: List<Order>
)
