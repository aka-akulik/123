package com.example.wishlistapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.wishlistapp.contact.Contact
import com.example.wishlistapp.viewmodel.ContactViewModel

@Composable
fun ContactScreen(viewModel: ContactViewModel){
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)){
        TextField(
            value = name,
            onValueChange = { name = it},
            label = { Text ("Имя")},
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = phoneNumber,
            onValueChange = {phoneNumber = it},
            label = { Text("Телефон") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = email,
            onValueChange = {email = it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                viewModel.addContact(Contact(name = name, phoneNumber = phoneNumber, email = email))
                name = ""
                phoneNumber= ""
                email = ""
            },
            modifier = Modifier.padding(top = 8.dp).fillMaxWidth()
        ) {
            Text("Добавить контакт")
        }

        Spacer(modifier = Modifier.height(16.dp))

        ContactList(contacts = viewModel.contacts.collectAsState().value, viewModel)

    }
}

@Composable
fun ContactList(contacts: List<Contact>, viewModel: ContactViewModel) {
    Column {
        contacts.forEach{ contact ->
            ContactItem(contact,viewModel)
        }
    }
}

@Composable
fun ContactItem(contact: Contact, viewModel: ContactViewModel) {
    var isEditing by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(contact.name) }
    var phoneNumber by remember { mutableStateOf(contact.phoneNumber) }
    var email by remember { mutableStateOf(contact.email) }

    if (isEditing){
        Column (modifier = Modifier.padding(8.dp)) {
            TextField(value = name, onValueChange = {name = it}, label = {Text("Имя")})
            TextField(value = phoneNumber, onValueChange = {phoneNumber = it}, label = {Text("Телефон")})
            TextField(value = email, onValueChange = {email = it}, label = {Text("Email")})

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                Button(onClick = {
                    viewModel.updateContact((contact.copy(name = name, phoneNumber = phoneNumber,email = email)))
                        isEditing = false
                }) {
                        Text("Сохранить")
                }
                Button(onClick = { isEditing = false }) {
                    Text("Отмена")
                }
            }

        }
    }  else {
        Row (modifier = Modifier.padding(8.dp)){
            Text( text = "${contact.name} - ${contact.phoneNumber} - ${contact.email}")
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick =  { isEditing = true }) {
                Icon( imageVector = Icons.Default.Edit, contentDescription = "Редактировать")
            }
            IconButton(onClick =  { viewModel.deleteContact(contact) }) {
                Icon( imageVector = Icons.Default.Delete, contentDescription = "Удалить")
            }
        }
    }
}
