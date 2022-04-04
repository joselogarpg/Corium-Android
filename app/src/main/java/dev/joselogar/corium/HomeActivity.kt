package dev.joselogar.corium

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.joselogar.corium.model.ProductProvider
import dev.joselogar.corium.ui.theme.Orange200
import dev.joselogar.corium.ui.theme.Purple200
import dev.joselogar.corium.view.ProductView

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Corium",
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(text = "Corium")
            }
        }
    ) {
        HomeBodyContent(navController)
    }
}

@Composable
fun HomeBodyContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                listOf(
                    Purple200,
                    Orange200
                )
            )),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProductView(product = ProductProvider.products.first())

        Text(text = "Corium")

        Button(onClick = { navController.navigate(route = Screens.ProductsScreen.route) }) {
            Text(text = "Productos")
        }
    }

    /*Column(
        modifier = Modifier.padding(30.dp)
            .clickable { navController.navigate(route = Screens.ProductsScreen.route) }
    ) {
        ProductView(product = ProductProvider.products.first())
    }*/
}

@Preview(showBackground = true)
@Composable
fun HomeDefaultPreview() {
    HomeBodyContent(navController = rememberNavController())
}