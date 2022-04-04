package dev.joselogar.corium

sealed class Screens(val route: String) {
    object LoginScreen: Screens(route = "login_screen")
    object SignupScreen: Screens(route = "signup_screen")
    object HomeScreen: Screens(route = "home_screen")
    object ProductsScreen: Screens(route = "products_screen")
}