package uz.gita.veryimportantexamwithgroup.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tapadoo.alerter.Alerter
import uz.gita.veryimportantexamwithgroup.R
import uz.gita.veryimportantexamwithgroup.data.models.StoreData
import uz.gita.veryimportantexamwithgroup.databinding.ScreenAddBinding
import uz.gita.veryimportantexamwithgroup.databinding.ScreenLoginBinding
import uz.gita.veryimportantexamwithgroup.ui.add.viewmodel.AddViewModel
import uz.gita.veryimportantexamwithgroup.ui.add.viewmodel.impl.AddViewModelImpl
import uz.gita.veryimportantexamwithgroup.ui.login.viewmodel.LoginViewModel
import uz.gita.veryimportantexamwithgroup.ui.login.viewmodel.impl.LoginViewModelImpl

class LoginScreen : Fragment(R.layout.screen_login) {
    private val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()
    private val viewBinding: ScreenLoginBinding by viewBinding(ScreenLoginBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.messageLiveData.observe(this, messageObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.btnSignIn.setOnClickListener {
            viewModel.signIn(viewBinding.login.text.toString(), viewBinding.password.text.toString())
        }
    }

    private val messageObserver = Observer<String> {
        Alerter.create(requireActivity())
            .setTitle("Message")
            .setText(it)
            .setBackgroundColorRes(R.color.green_color)
            .show()
    }
}