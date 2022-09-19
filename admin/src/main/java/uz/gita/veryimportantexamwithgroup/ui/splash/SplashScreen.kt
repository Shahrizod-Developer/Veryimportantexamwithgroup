package uz.gita.veryimportantexamwithgroup.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.veryimportantexamwithgroup.BuildConfig
import uz.gita.veryimportantexamwithgroup.R
import uz.gita.veryimportantexamwithgroup.databinding.ScreenSplashBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val viewBinding: ScreenSplashBinding by viewBinding(ScreenSplashBinding::bind)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.textVersion.text = "version " + BuildConfig.VERSION_NAME
        lifecycleScope.launch {
            delay(2000)
            findNavController().navigate(SplashScreenDirections.actionSplashScreenToLoginScreen())
        }
    }
}