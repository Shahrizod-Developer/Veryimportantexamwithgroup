package uz.gita.store.presentation.view_model

import androidx.lifecycle.LiveData

interface AddProductViewModel {
    val saveButton: LiveData<Unit>
}