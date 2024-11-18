package com.example.wishlistapp.items

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.wishlistapp.R
import com.example.wishlistapp.model.WishModel
import com.example.wishlistapp.utils.Screens
import com.example.wishlistapp.viewmodel.WishesViewModel


@Composable
fun WishItem(wishModel: WishModel, navHostController: NavHostController, viewModel: WishesViewModel){

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    viewModel.setEditingWish(wishModel)
                    navHostController.navigate(Screens.wishScreen)
                },
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            listOf(
                                colorResource(R.color.firstCardColor),
                                colorResource(R.color.secondCardColor)
                            )
                        ),
                        shape = CardDefaults.shape
                    )
                    .padding(8.dp)
            ) {
                Text(
                    text = wishModel.name,
                    color = colorResource(R.color.cardTextColor)
                )
                Text(
                    text = wishModel.description,
                    color = colorResource(R.color.cardTextColor)
                )
            }

    }

}

