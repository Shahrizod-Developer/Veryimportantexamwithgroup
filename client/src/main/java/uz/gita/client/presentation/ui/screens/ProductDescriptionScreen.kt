
 package uz.gita.client.presentation.ui.screens


    import android.os.Bundle
    import android.view.View
    import androidx.fragment.app.Fragment
    import by.kirich1409.viewbindingdelegate.viewBinding
    import uz.gita.client.R
    import uz.gita.client.databinding.ScreenProductDescriptionBinding

    class ProductDescriptionScreen : Fragment(R.layout.screen_product_description) {
        private val viewBinding by viewBinding(ScreenProductDescriptionBinding::bind)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        }
    }
