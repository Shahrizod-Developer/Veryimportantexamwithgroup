package uz.gita.veryimportantexamwithgroup.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tapadoo.alerter.Alerter
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.veryimportantexamwithgroup.R
import uz.gita.veryimportantexamwithgroup.data.models.StoreData
import uz.gita.veryimportantexamwithgroup.databinding.ScreenLoginBinding
import uz.gita.veryimportantexamwithgroup.databinding.ScreenMainBinding
import uz.gita.veryimportantexamwithgroup.ui.login.viewmodel.LoginViewModel
import uz.gita.veryimportantexamwithgroup.ui.login.viewmodel.impl.LoginViewModelImpl
import uz.gita.veryimportantexamwithgroup.ui.main.adapter.StoresAdapter
import uz.gita.veryimportantexamwithgroup.ui.main.viewmodel.MainViewModel
import uz.gita.veryimportantexamwithgroup.ui.main.viewmodel.impl.MainViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private val viewBinding: ScreenMainBinding by viewBinding(ScreenMainBinding::bind)
    private val adapter: StoresAdapter by lazy { StoresAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.messageLiveData.observe(this, messageObserver)
        viewModel.getStores().observe(this, getObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.addStore.setOnClickListener {
            viewModel.openAdd()
        }
        adapter.setItemEditListener { viewModel.openEdit(it) }
        adapter.setItemDeleteListener { viewModel.deleteStore(it) }
    }

    private val messageObserver = Observer<String> {
        Alerter.create(requireActivity())
            .setTitle("Message")
            .setText(it)
            .setBackgroundColorRes(R.color.green_color)
            .show()
    }

    private val getObserver = Observer<Result<List<StoreData>>> { result ->
        when {
            result.isFailure -> {
                viewModel.failurMessage("There was an error downloading, please check your internet connection and try again")
            }
            result.isSuccess -> {
                adapter.submitList(result.getOrNull())
            }
        }

    }
}