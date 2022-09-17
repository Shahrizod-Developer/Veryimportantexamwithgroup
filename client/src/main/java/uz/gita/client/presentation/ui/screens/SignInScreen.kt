package uz.gita.client.presentation.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.client.R
import uz.gita.client.databinding.ScreenSignInBinding

class SignInScreen : Fragment(R.layout.screen_sign_in) {
    private val viewBinding: ScreenSignInBinding by viewBinding(ScreenSignInBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}