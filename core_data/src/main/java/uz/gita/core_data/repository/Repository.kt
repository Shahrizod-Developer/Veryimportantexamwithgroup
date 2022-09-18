package uz.gita.core_data.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.core_data.data.local.*

// Created by Jamshid Isoqov an 9/17/2022
interface Repository {

    suspend fun addStore(storeData: StoreData)

    suspend fun loginSore(storeData: StoreData)

    suspend fun loginUser(userData: UserData): Int

    suspend fun registerUser(userData: UserData): Boolean

    fun getCategories(): Flow<List<ProductCategoryData>>

    fun getProductsByCategory(productCategoryData: ProductCategoryData): Flow<List<ProductData>>

    suspend fun sellProducts(ordersData: OrdersData)

    suspend fun updateOrders(ordersData: OrdersData)

    fun searchProducts(query: String): Flow<List<ProductData>>

}