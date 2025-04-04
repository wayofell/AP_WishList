package com.mado.wishlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mado.wishlist.ui.theme.WishListTheme
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.mado.wishlist.data.WishDatabase
import com.mado.wishlist.data.WishRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WishListTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                ) { innerPadding ->
                    val navController: NavHostController = rememberNavController()
                    val database = WishDatabase.getDatabase(applicationContext)
                    val repository = WishRepository(database.wishDao())
                    val viewModel: WishViewModel =
                        viewModel(factory = WishViewModelFactory(repository))
                    Navigation(
                        navController = navController,
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

class WishViewModelFactory(private val repository: WishRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WishViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WishViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    WishListTheme {
//        HomeView(navController = rememberNavController(), viewModel = viewModel())
//    }
//}