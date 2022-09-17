package uz.gita.veryimportantexamwithgroup.ui.add

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
import uz.gita.veryimportantexamwithgroup.ui.add.viewmodel.AddViewModel
import uz.gita.veryimportantexamwithgroup.ui.add.viewmodel.impl.AddViewModelImpl

class AddScreen : Fragment(R.layout.screen_add) {

    private val viewModel: AddViewModel by viewModels<AddViewModelImpl>()
    private val viewBinding: ScreenAddBinding by viewBinding(ScreenAddBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.messageLiveData.observe(this, messageObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.btnAdd.setOnClickListener {
            val storeData = StoreData(
                name = viewBinding.name.text.toString(),
                login = viewBinding.login.text.toString(),
                password = viewBinding.password.text.toString()
            )
            viewModel.addStore(storeData)
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