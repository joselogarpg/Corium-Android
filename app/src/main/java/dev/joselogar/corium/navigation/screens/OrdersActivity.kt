package dev.joselogar.corium.navigation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.toObject
import dev.joselogar.corium.model.ModelOrder
import dev.joselogar.corium.model.ProviderOrder
import dev.joselogar.corium.view.OrderView

/* OrdersScreen
* navController = NavController manages app navigation within a NavHost.
*/
@Composable
fun OrdersScreen(
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
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Orders",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            navController.navigate(route = Screens.HomeScreen.route)
                        },
                    tint = MaterialTheme.colors.onSecondary
                )

                Spacer(
                    modifier = Modifier
                        .width(16.dp)
                )

                Text(
                    text = "Orders",
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.h6
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(route = Screens.AddOrderScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add order",
                    modifier = Modifier
                        .size(32.dp),
                    tint = MaterialTheme.colors.onSecondary
                )
            }
        }
    ) {
        OrdersBodyContent(
            LocalContext.current
        )
    }
}

/* OrdersBodyContent
* context = Interface to global information about an application environment (toast).
*/
@Composable
fun OrdersBodyContent(
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
        /*val orders =
            loadOrders(
                context
            )*/

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {/*
            items(orders.size) { index ->
                OrderView(orders[index])
            }*/

            items(ProviderOrder.orders.size) { index ->
                OrderView(ProviderOrder.orders[index])
            }

            item {
                Spacer(
                    modifier = Modifier
                        .height(32.dp)
                )
            }
        }
    }
}

/* loadOrder
* context = Interface to global information about an application environment (toast).
*/
@Composable
fun loadOrder(
    context: Context,
): MutableList<ModelOrder> {
    var orders = mutableListOf<ModelOrder>()

    val source = Source.CACHE
    FirebaseFirestore.getInstance()
        .collection("orders")
        .whereEqualTo("uid", FirebaseAuth.getInstance().currentUser?.uid)
        .get(source)
        .addOnSuccessListener { documents ->
            for (document in documents) {
                orders.add(document.toObject())
            }

            Toast.makeText(context, "Orders loaded", Toast.LENGTH_LONG).show()
        }
        .addOnFailureListener { exception ->
            Toast.makeText(context, "Orders not loaded", Toast.LENGTH_LONG).show()
        }

    return orders
}

/* loadOrders
* context = Interface to global information about an application environment (toast).
*/
@Composable
fun loadOrders(
    context: Context,
): MutableList<ModelOrder> {
    var orders = mutableListOf<ModelOrder>()

    val source = Source.CACHE
    FirebaseFirestore.getInstance()
        .collection("orders")
        .whereEqualTo("uid", FirebaseAuth.getInstance().currentUser?.uid)
        .get(source)
        .addOnSuccessListener { documents ->
            for (document in documents) {
                orders.add(document.toObject())
            }

            Toast.makeText(context, "Orders loaded", Toast.LENGTH_LONG).show()
        }
        .addOnFailureListener { exception ->
            Toast.makeText(context, "Orders not loaded", Toast.LENGTH_LONG).show()
        }

    return orders
}