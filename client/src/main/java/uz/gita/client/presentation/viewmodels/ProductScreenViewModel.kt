package uz.gita.client.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.core_data.data.local.ProductCategoryData
import uz.gita.core_data.data.local.ProductData

interface ProductScreenViewModel {
    val categoryLive: LiveData<ProductCategoryData>

    val productsLiveData: LiveData<List<ProductData>>

    val productLive: MutableLiveData<ProductData>

    val gotoCart: LiveData<Unit>

    fun gotoProductDetails(productData: ProductData)

    fun gotoCart()

    fun setCategoryData(productCategoryData: ProductCategoryData)
    fun setProductsList(productCategoryData: ProductCategoryData)
}