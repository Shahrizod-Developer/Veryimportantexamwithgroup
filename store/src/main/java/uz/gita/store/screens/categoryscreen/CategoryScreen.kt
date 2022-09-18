package uz.gita.store.screens.categoryscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.store.R
import uz.gita.store.databinding.FragmentCategoryScreenBinding

class CategoryScreen :Fragment(R.layout.fragment_category_screen){
    private val viewBinding: FragmentCategoryScreenBinding by viewBinding(FragmentCategoryScreenBinding::bind)
    private val navController by lazy { findNavController() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.addBtn.setOnClickListener{
            navController.navigate(CategoryScreenDirections.actionCategoryScreenToAddCategoryScreen())
        }
        viewBinding.addBtn2.setOnClickListener{
            navController.navigate(CategoryScreenDirections.actionCategoryScreenToOrdersScreen())
        }
    }
}