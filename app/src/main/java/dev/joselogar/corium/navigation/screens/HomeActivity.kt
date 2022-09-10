package dev.joselogar.corium.navigation.screens

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.joselogar.corium.Navigation
import dev.joselogar.corium.model.ProviderOrder
import dev.joselogar.corium.model.ProviderProduct
import dev.joselogar.corium.ui.theme.CoriumTheme
import dev.joselogar.corium.view.OrderView
import dev.joselogar.corium.view.ProductView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CoriumTheme {
                Navigation()
            }
        }
    }
}

/* HomeScreen
* navController = NavController manages app navigation within a NavHost.
*/
@Composable
fun HomeScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 0.dp
            ) {
                Spacer(
                    modifier = Modifier
                        .width(8.dp)
                )

                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Corium",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            navController.navigate(route = Screens.HomeScreen.route)
                        },
                    tint = MaterialTheme.colors.onSecondary
                )

                Spacer(
                    modifier = Modifier
                        .width(8.dp)
                )

                Text(
                    text = "Corium",
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = "Orders",
                    modifier = Modifier
                        .padding(
                            start = 16.dp
                        )
                        .clickable {
                            navController.navigate(route = Screens.OrdersScreen.route)
                        },
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = "Products",
                    modifier = Modifier
                        .padding(
                            start = 16.dp
                        )
                        .clickable {
                            navController.navigate(route = Screens.ProductsScreen.route)
                        },
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    ) {
        HomeBodyContent(
            LocalContext.current
        )
    }
}

/* HomeBodyContent
* context = Interface to global information about an application environment (toast).
*/
@Composable
fun HomeBodyContent(
    context: Context
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colors.primary,
                        MaterialTheme.colors.secondary
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*var orders =
            loadOrder(
                context
            )*/

        var products =
            loadProduct(
                context
            )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /*if (orders.size >= 1)
                item {
                    OrderView(orders.random())
                }*/

            if (products.size >= 1)
                item {
                    ProductView(products.random())
                }

            item {
                OrderView(ProviderOrder.orders.random())

                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )
            }

            item {
                ProductView(ProviderProduct.products.random())

                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )
            }
        }
    }
}