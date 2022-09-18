package uz.gita.veryimportantexamwithgroup.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tapadoo.alerter.Alerter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.veryimportantexamwithgroup.R
import uz.gita.veryimportantexamwithgroup.data.models.StoreData
import uz.gita.veryimportantexamwithgroup.databinding.ScreenAddBinding
import uz.gita.veryimportantexamwithgroup.databinding.ScreenLoginBinding
import uz.gita.veryimportantexamwithgroup.ui.add.viewmodel.AddViewModel
import uz.gita.veryimportantexamwithgroup.ui.add.viewmodel.impl.AddViewModelImpl
import uz.gita.veryimportantexamwithgroup.ui.login.viewmodel.LoginViewModel
import uz.gita.veryimportantexamwithgroup.ui.login.viewmodel.impl.LoginViewModelImpl

@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {
    private val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()
    private val viewBinding: ScreenLoginBinding by viewBinding(ScreenLoginBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.messageLiveData.observe(this, messageObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.btnSignIn.clicks()
            .onEach { viewModel.signIn(viewBinding.login.text.toString(), viewBinding.password.text.toString()) }
            .launchIn(lifecycleScope)
    }

    private val messageObserver = Observer<String> {
        Alerter.create(requireActivity())
            .setText(it)
            .setBackgroundColorRes(R.color.green_color)
            .show()
    }
}