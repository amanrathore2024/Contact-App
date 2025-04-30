package com.example.contacts.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts.data.DAO.DeleteContactDao
import com.example.contacts.data.entities.DeletedContact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeletedContactViewModel @Inject constructor(
    private val deletedContactDao: DeleteContactDao
) : ViewModel() {

    private val _deletedContacts = MutableStateFlow<List<DeletedContact>>(emptyList())
    val deletedContacts: StateFlow<List<DeletedContact>> = _deletedContacts

    init {
        getDeletedContacts()
    }

    private fun getDeletedContacts() {
        viewModelScope.launch {
            deletedContactDao.getDeletedContact().collect {
                _deletedContacts.value = it
            }
        }
    }


}



