package dev.joselogar.corium

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.joselogar.corium.navigation.screens.*

@Composable
fun Navigation() {
    val navController = rememberNavController() // viewmodel

    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.route
    ) {
        composable(route = Screens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = Screens.SignupScreen.route) {
            SignupScreen(navController)
        }
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = Screens.ProductsScreen.route) {
            ProductsScreen(navController)
        }
        composable(route = Screens.OrdersScreen.route) {
            OrdersScreen(navController)
        }
        composable(route = Screens.AddProductScreen.route) {
            AddProductScreen(navController)
        }
        composable(route = Screens.AddOrderScreen.route) {
            AddOrderScreen(navController)
        }
    }
}