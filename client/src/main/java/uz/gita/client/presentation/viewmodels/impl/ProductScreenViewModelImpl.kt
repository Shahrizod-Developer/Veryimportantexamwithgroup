package uz.gita.client.presentation.viewmodels.impl

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.client.presentation.viewmodels.ProductScreenViewModel
import uz.gita.core_data.data.local.ProductCategoryData
import uz.gita.core_data.data.local.ProductData
import uz.gita.core_data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class ProductScreenViewModelImpl @Inject constructor(val repository: Repository) : ProductScreenViewModel, ViewModel() {
    override val categoryLive = MutableLiveData<ProductCategoryData>()
    override val productsLiveData = MutableLiveData<List<ProductData>>()
    override val productLive = MutableLiveData<ProductData>()
    override val gotoCart = MutableLiveData<Unit>()


    override fun gotoProductDetails(productData: ProductData) {
        productLive.value = productData
    }

    override fun gotoCart() {
        gotoCart.value = Unit
    }

    override fun setCategoryData(productCategoryData: ProductCategoryData) {
        categoryLive.value = productCategoryData
    }

    override fun setProductsList(productCategoryData: ProductCategoryData) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.getProductsByCategory(productCategoryData).onEach {
                productsLiveData.postValue(
                    it
                )
            }.collect()
        }


    }
}