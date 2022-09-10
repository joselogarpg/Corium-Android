package dev.joselogar.corium.navigation.screens

sealed class Screens(val route: String) {
    object LoginScreen: Screens(route = "login_screen")
    object SignupScreen: Screens(route = "signup_screen")
    object HomeScreen: Screens(route = "home_screen")
    object ProductsScreen: Screens(route = "products_screen")
    object OrdersScreen: Screens(route = "orders_screen")
    object AddProductScreen: Screens(route = "add_product_screen")
    object AddOrderScreen: Screens(route = "add_order_screen")
}