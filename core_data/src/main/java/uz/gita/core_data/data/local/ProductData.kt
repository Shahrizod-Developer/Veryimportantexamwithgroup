package uz.gita.core_data.data.local

// Created by Jamshid Isoqov an 9/17/2022
data class ProductData(
    val id: String,
    val categoryId: String,
    val name: String,
    val description: String,
    val photos: List<String>,
    val sell: String,
    val attrs: List<Pair<String, String>>
)
