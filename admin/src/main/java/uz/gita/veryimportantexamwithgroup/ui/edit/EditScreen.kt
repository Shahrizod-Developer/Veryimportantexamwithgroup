package uz.gita.veryimportantexamwithgroup.ui.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tapadoo.alerter.Alerter
import uz.gita.veryimportantexamwithgroup.R
import uz.gita.veryimportantexamwithgroup.data.models.StoreData
import uz.gita.veryimportantexamwithgroup.databinding.ScreenEditBinding
import uz.gita.veryimportantexamwithgroup.ui.edit.viewmodel.EditViewModel
import uz.gita.veryimportantexamwithgroup.ui.edit.viewmodel.impl.EditViewModelImpl

class EditScreen : Fragment(R.layout.screen_edit) {
    private val viewModel: EditViewModel by viewModels<EditViewModelImpl>()
    private val viewBinding: ScreenEditBinding by viewBinding(ScreenEditBinding::bind)
    private var data: StoreData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            data = it.getSerializable("data") as StoreData
        }

        viewModel.messageLiveData.observe(this, messageObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.btnEdit.setOnClickListener {
            val storeData = StoreData(
                id = data!!.id,
                name = viewBinding.name.text.toString(),
                login = viewBinding.login.text.toString(),
                password = viewBinding.password.text.toString()
            )
            viewModel.updateStore(storeData)
            viewModel.navigateUp()
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