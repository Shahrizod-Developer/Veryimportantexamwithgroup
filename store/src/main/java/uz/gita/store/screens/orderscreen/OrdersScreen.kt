package uz.gita.store.screens.orderscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.store.R
import uz.gita.store.databinding.FragmentCategoryScreenBinding
import uz.gita.store.databinding.ScreenOrdersBinding
import uz.gita.store.screens.categoryscreen.CategoryScreenDirections

class OrdersScreen:Fragment(R.layout.screen_orders) {
    private val viewBinding: ScreenOrdersBinding by viewBinding(
        ScreenOrdersBinding::bind)
    private val navController by lazy { findNavController() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.addBtnorders.setOnClickListener{
            navController.navigate(OrdersScreenDirections.actionOrdersScreenToAddCategoryScreen())
        }
    }
}