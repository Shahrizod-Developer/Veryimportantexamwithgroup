package uz.gita.client.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import uz.gita.client.R
import uz.gita.client.adapters.ProductAdapter
import uz.gita.client.presentation.viewmodels.ProductScreenViewModel
import uz.gita.client.presentation.viewmodels.impl.ProductScreenViewModelImpl
import uz.gita.core_data.data.local.ProductCategoryData


class ProductScreen : Fragment(R.layout.fragment_product_screen) {

    private lateinit var recyclerView: RecyclerView
    private val adabpter: ProductAdapter by lazy { ProductAdapter() }

    private val viewModel: ProductScreenViewModel by viewModels<ProductScreenViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setCategoryData(
            requireArguments().getSerializable("CategoryData") as ProductCategoryData
        )

        viewModel.categoryLive.observe(this){
            viewModel.setProductsList(it)
        }

        viewModel.productLive.observe(this){
            findNavController()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            recyclerView = findViewById(R.id.product_list)
            recyclerView.adapter = adabpter
        }

        viewModel.productsLiveData.observe(viewLifecycleOwner){
            adabpter.submitList(it)
        }
    }

}