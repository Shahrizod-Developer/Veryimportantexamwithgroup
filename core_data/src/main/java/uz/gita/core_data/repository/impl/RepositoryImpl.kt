package uz.gita.core_data.repository.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.core_data.data.local.*
import uz.gita.core_data.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

    override suspend fun addStore(storeData: StoreData) {

    }

    override suspend fun loginSore(storeData: StoreData) {
        TODO("Not yet implemented")
    }

    override suspend fun loginUser(userData: UserData): Int {
        TODO("Not yet implemented")
    }

    override suspend fun registerUser(userData: UserData): Boolean {
        TODO("Not yet implemented")
    }

    override fun getCategories(): Flow<List<ProductCategoryData>> {
        TODO("Not yet implemented")
    }

    override fun getProductsByCategory(productCategoryData: ProductCategoryData): Flow<List<ProductData>> {
        TODO("Not yet implemented")
    }

    override suspend fun sellProducts(ordersData: OrdersData) {
        TODO("Not yet implemented")
    }

    override suspend fun updateOrders(ordersData: OrdersData) {
        TODO("Not yet implemented")
    }

    override fun searchProducts(query: String): Flow<List<ProductData>> {
        TODO("Not yet implemented")
    }
}