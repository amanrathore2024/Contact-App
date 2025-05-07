package com.example.contacts.ViewModels

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contact.data.entities.Contact
import com.example.contacts.Repository
import com.example.contacts.utils.toDeletedContact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    val repository: Repository
   // val context: Context
): ViewModel() {
    private val _state = MutableStateFlow(AppState())
    val state = _state.asStateFlow()

    var Fname = mutableStateOf("")
    var lname = mutableStateOf("")
    var phoneNo = mutableStateOf("")
    var id = mutableStateOf<Int>(0)
    var email = mutableStateOf("")

    var getAllContact : Flow<List<Contact>>
    init {
        getAllContact =  repository.getAllContact()
        viewModelScope.launch {
            getAllContact.collect {
                _state.value = _state.value.copy(contactList = it)
            }
        }
    }
 
    fun upsertContact(contact: Contact){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.upsertcon(contact)
            }
            catch (e: Exception){
                _state.value = _state.value.copy(error = e.message)
            }
        }
    }

    fun delContact(contact: Contact){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletecon(contact)
        }
    }

    fun upsertDeletedContact(contact: Contact) {
        viewModelScope.launch {
            val deletedContact = contact.toDeletedContact()
            repository.upsertDeletedContacts(deletedContact)
        }
    }

//    fun restoreContact(contact: DeletedContact) {
//        viewModelScope.launch {
//            // Convert DeletedContact to Contact (map the fields appropriately)
//            val restoredContact = Contact(
//                id = contact.id,
//                firstName = contact.firstName,
//                lastName = contact.lastName,
//                phoneNumber = contact.phoneNumber,
//                email = contact.email
//            )
//



}

data class AppState(
    var loading : Boolean = false,
    var contactList : List<Contact> = emptyList(),
    var error : String? = null
)