package uz.gita.store.presentation.view_model.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.store.presentation.view_model.AddProductViewModel
import javax.inject.Inject

@HiltViewModel
class AddProductViewModelImpl @Inject constructor() : AddProductViewModel {
    override val saveButton: MutableLiveData<Unit> = MutableLiveData()

    init {

    }

    fun saveButton() {
        saveButton.value = Unit
    }

}