package dev.joselogar.corium

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.joselogar.corium.ui.theme.Orange200
import dev.joselogar.corium.ui.theme.Purple200

@Composable
fun ProductsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Corium",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(text = "Productos")
            }
        }
    ) {
        ProductsBodyContent(navController)
    }
}

@Composable
fun ProductsBodyContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(
                listOf(
                    Purple200,
                    Orange200
                )
            )),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Productos")

        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Corium")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsDefaultPreview() {
    ProductsBodyContent(navController = rememberNavController())
}