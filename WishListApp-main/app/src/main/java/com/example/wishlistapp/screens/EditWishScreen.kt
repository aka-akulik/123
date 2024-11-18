package com.example.wishlistapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.wishlistapp.R
import com.example.wishlistapp.viewmodel.WishesViewModel

@Composable
fun EditWishScreen(
    viewModel: WishesViewModel,
    navHostController: NavHostController
){
    val wishName by viewModel.wishName.collectAsState()
    val wishDescription by viewModel.wishDescription.collectAsState()
    val editingWish by viewModel.editingWish.collectAsState()

    LaunchedEffect(
        editingWish
    ) {
        viewModel.changeWishName(editingWish!!.name)
        viewModel.changeWishDescription(editingWish!!.description)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(R.color.white),
        topBar = {
            WishTopBar(editingWish!!.name, viewModel, navHostController)
        },

    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = wishName,
                onValueChange = {
                    name ->
                    viewModel.changeWishName(name)
                }
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = wishDescription,
                onValueChange = {
                    description ->
                    viewModel.changeWishDescription(description)
                }
            )
            Button(
                onClick = {
                    viewModel.updateWish()
                    navHostController.popBackStack()
                }
            ) {
                Text("Update")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishTopBar(title: String, viewModel: WishesViewModel, navHostController: NavHostController) {
    TopAppBar(
        title = {
            Text(
                text = title,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navHostController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = ""
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = colorResource(R.color.LightBlue),
            titleContentColor = Color.White
        )
    )
}
