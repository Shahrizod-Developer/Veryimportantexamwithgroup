package uz.gita.store.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.children
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.store.R
import uz.gita.store.databinding.AddProductScreenBinding

class AddProductScreen : Fragment(R.layout.add_product_screen) {
    val viewBinding: AddProductScreenBinding by viewBinding(AddProductScreenBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        for(i in viewBinding.atributs.children){
//        }

    }
}