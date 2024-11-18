package com.example.wishlistapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapp.contact.Contact
import com.example.wishlistapp.contact.ContactDao
import com.example.wishlistapp.model.WishDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContactViewModel () : ViewModel(){
    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> =_contacts
    private lateinit var contactDao: ContactDao

    init {

    }

    private fun loadContacts(){
        viewModelScope.launch{
            viewModelScope.launch {
                _contacts.value = contactDao.getAllContacts()
            }
        }
    }

    fun addContact(contact: Contact){
        viewModelScope.launch {
            contactDao.addContact(contact)
            loadContacts()
        }
    }

    fun updateContact(contact: Contact){
        viewModelScope.launch {
            contactDao.updateContact(contact)
            loadContacts()
        }
    }

    fun deleteContact(contact: Contact){
        viewModelScope.launch {
            contactDao.deleteContact(contact)
            loadContacts()
        }
    }

    fun initialize(contactDao: ContactDao) {
        this.contactDao = contactDao
        loadContacts()
    }
}
