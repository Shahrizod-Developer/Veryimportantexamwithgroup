package uz.gita.veryimportantexamwithgroup.ui.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tapadoo.alerter.Alerter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.veryimportantexamwithgroup.R
import uz.gita.veryimportantexamwithgroup.data.models.StoreData
import uz.gita.veryimportantexamwithgroup.databinding.ScreenAddBinding
import uz.gita.veryimportantexamwithgroup.ui.add.viewmodel.AddViewModel
import uz.gita.veryimportantexamwithgroup.ui.add.viewmodel.impl.AddViewModelImpl
import java.util.*

@AndroidEntryPoint
class AddScreen : Fragment(R.layout.screen_add) {

    private val viewModel: AddViewModel by viewModels<AddViewModelImpl>()
    private val viewBinding: ScreenAddBinding by viewBinding(ScreenAddBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.messageLiveData.observe(this, messageObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.btnAdd.clicks()
            .onEach {
                val storeData = StoreData(
                    id = UUID.randomUUID().toString(),
                    name = viewBinding.name.text.toString(),
                    login = viewBinding.login.text.toString(),
                    password = viewBinding.password.text.toString()
                )
                viewModel.addStore(storeData)
            }
            .launchIn(lifecycleScope)
    }

    private val messageObserver = Observer<String> {
        Alerter.create(requireActivity())
            .setText(it)
            .setBackgroundColorRes(R.color.green_color)
            .show()
    }
}