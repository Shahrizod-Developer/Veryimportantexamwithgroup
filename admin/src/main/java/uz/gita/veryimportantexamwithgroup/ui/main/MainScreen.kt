package uz.gita.veryimportantexamwithgroup.ui.main

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tapadoo.alerter.Alerter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.veryimportantexamwithgroup.R
import uz.gita.veryimportantexamwithgroup.data.models.StoreData
import uz.gita.veryimportantexamwithgroup.databinding.ScreenDialogBinding
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
        viewModel.getData.observe(this, getObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.listStore.adapter = adapter

        viewBinding.addStore.clicks()
            .onEach { viewModel.openAdd() }
            .launchIn(lifecycleScope)
        adapter.setItemEditListener { result -> flowOf(1).onEach { viewModel.openEdit(result) }.launchIn(lifecycleScope) }
        adapter.setItemDeleteListener { store ->
            val builder = AlertDialog.Builder(requireContext())
            val binding = ScreenDialogBinding.inflate(layoutInflater)
            builder.setView(binding.root)
            builder.setCancelable(false)
            val alertDialog = builder.create()
            alertDialog.window!!.setBackgroundDrawable(
                ColorDrawable(
                    Color.TRANSPARENT
                )
            )
            binding.btnYes.setOnClickListener {
                viewModel.deleteStore(store)
                alertDialog.dismiss()
            }
            binding.btnNo.setOnClickListener {
                alertDialog.dismiss()
            }
            alertDialog.show()
        }
    }

    private val messageObserver = Observer<String> {
        Alerter.create(requireActivity())
            .setText(it)
            .setBackgroundColorRes(R.color.green_color)
            .show()
    }

    private val getObserver = Observer<Result<List<StoreData>>> { result ->
        when {
            result.isFailure -> {
                viewModel.failurMessage("Yuklashda xatolik yuz berdi. Qurilma internetga ulanganligini tekshiring!")
            }
            result.isSuccess -> {
                adapter.submitList(result.getOrNull())
            }
        }

    }
}