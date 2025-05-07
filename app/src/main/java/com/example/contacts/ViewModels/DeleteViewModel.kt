package com.example.contacts.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts.Repository
import com.example.contacts.data.entities.DeletedContact
import com.example.contacts.utils.toContact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeletedContactViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _deletedContactslist = MutableStateFlow<List<DeletedContact>>(emptyList())
    val deletedContactslist: StateFlow<List<DeletedContact>> = _deletedContactslist

    init {
        getDeletedContacts()
    }

    private fun getDeletedContacts() {
        viewModelScope.launch {
            repository.getDeletedContacts().collect {
                _deletedContactslist.value = it
            }
        }
    }

    fun deleteContact( contact: DeletedContact){
        viewModelScope.launch {
            repository.deleteDeletedContact(contact)
        }
    }

    fun restoreContact( contact: DeletedContact){
        viewModelScope.launch {
            repository.upsertcon(contact.toContact())
        }
    }




}



