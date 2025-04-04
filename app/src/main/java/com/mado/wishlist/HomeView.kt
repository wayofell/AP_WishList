package com.mado.wishlist

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mado.wishlist.data.Wish
import androidx.navigation.NavController

@Composable
fun HomeView(navController: NavController, viewModel: WishViewModel) {
    val context = LocalContext.current
    val wishes by viewModel.allWishes.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            AppBarView(
                title = "Wishlist",
                onNavClicked = {
                    Toast.makeText(context, "Button Clicked", Toast.LENGTH_LONG).show()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                contentColor = Color.White,
                backgroundColor = Color.Black,
                onClick = {
                    navController.navigate(Screen.AddScreen.route)
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(wishes) { wish ->
                WishItem(
                    wish = wish,
                    onDelete = {
                        viewModel.deleteWish(wish)
                        Toast.makeText(context, "Wish deleted", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}

// Удаление при свайпе справа налево

//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun WishItem(wish: Wish, onDelete: () -> Unit) {
//    val dismissState = rememberDismissState(
//        confirmStateChange = {
//            if (it == DismissValue.DismissedToEnd) {
//                onDelete()
//                true
//            } else {
//                false
//            }
//        }
//    )
//
//    SwipeToDismiss(
//        state = dismissState,
//        directions = setOf(DismissDirection.StartToEnd),
//        dismissThresholds = { FractionalThreshold(0.5f) },
//        background = {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(10.dp)
//                    .background(Color.Red),
//                contentAlignment = Alignment.CenterStart
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Delete,
//                    contentDescription = "Delete",
//                    tint = Color.White,
//                    modifier = Modifier.padding(start = 16.dp)
//                )
//            }
//        },
//        dismissContent = {
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 8.dp, start = 8.dp, end = 8.dp),
//                elevation = 10.dp,
//                backgroundColor = Color.White
//            ) {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
//                    Text(text = wish.description)
//                }
//            }
//        }
//    )
//}

// Удаление при свайпе слева направо
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WishItem(wish: Wish, onDelete: () -> Unit) {
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToStart) {
                onDelete()
                true
            } else {
                false
            }
        }
    )

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.EndToStart),
        dismissThresholds = { FractionalThreshold(0.5f) },
        background = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .background(Color.Red),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.White,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        },
        dismissContent = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp),
                elevation = 10.dp,
                backgroundColor = Color.White
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
                    Text(text = wish.description)
                }
            }
        }
    )
}
