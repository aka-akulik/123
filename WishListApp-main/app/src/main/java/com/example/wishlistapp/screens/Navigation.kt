package com.example.wishlistapp.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wishlistapp.utils.Graph
import com.example.wishlistapp.utils.Screens
import com.example.wishlistapp.viewmodel.ContactViewModel
import com.example.wishlistapp.viewmodel.WishesViewModel

@Composable
fun Navigation() {
    val navigationController = rememberNavController()
    val viewModel: WishesViewModel = viewModel()
    val contactViewModel: ContactViewModel = viewModel()
    contactViewModel.initialize(Graph.database.getContactDao())
    NavHost(
        navController = navigationController,
        startDestination = Screens.mainScreen
    ){
        composable(
            route = Screens.mainScreen
        ){
            MainScreen(viewModel, navigationController)
        }
        composable(
            route = Screens.wishScreen
        ){
            EditWishScreen(viewModel, navigationController)
        }
        composable(
            route = Screens.contactScreen
        ) {
            ContactScreen(contactViewModel)
        }
    }
}