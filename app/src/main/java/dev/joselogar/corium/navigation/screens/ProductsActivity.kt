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
import dev.joselogar.corium.model.ModelProduct
import dev.joselogar.corium.model.ProviderProduct
import dev.joselogar.corium.view.ProductView

/* ProductsScreen
* navController = NavController manages app navigation within a NavHost.
*/
@Composable
fun ProductsScreen(
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
                    contentDescription = "Products",
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
                    text = "Products",
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.h6
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(route = Screens.AddProductScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add product",
                    modifier = Modifier
                        .size(32.dp),
                    tint = MaterialTheme.colors.onSecondary
                )
            }
        }
    ) {
        ProductsBodyContent(
            LocalContext.current
        )
    }
}

/* ProductsBodyContent
* context = Interface to global information about an application environment (toast).
*/
@Composable
fun ProductsBodyContent(
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
        val products =
            loadProducts(
                context
            )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(products.size) { index ->
                ProductView(products[index])
            }

            items(ProviderProduct.products.size) { index ->
                ProductView(ProviderProduct.products[index])
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

/* loadProduct
* context = Interface to global information about an application environment (toast).
*/
@Composable
fun loadProduct(
    context: Context,
): MutableList<ModelProduct> {
    var products = mutableListOf<ModelProduct>()

    val source = Source.CACHE
    FirebaseFirestore.getInstance()
        .collection("products")
        .whereEqualTo("uid", FirebaseAuth.getInstance().currentUser?.uid)
        .get(source)
        .addOnSuccessListener { documents ->
            for (document in documents) {
                products.add(document.toObject())
            }

            Toast.makeText(context, "Products loaded", Toast.LENGTH_LONG).show()
        }
        .addOnFailureListener { exception ->
            Toast.makeText(context, "Products not loaded", Toast.LENGTH_LONG).show()
        }

    return products
}

/* loadProducts
* context = Interface to global information about an application environment (toast).
*/
@Composable
fun loadProducts(
    context: Context,
): MutableList<ModelProduct> {
    var products = mutableListOf<ModelProduct>()

    val source = Source.CACHE
    FirebaseFirestore.getInstance()
        .collection("products")
        .whereEqualTo("uid", FirebaseAuth.getInstance().currentUser?.uid)
        .get(source)
        .addOnSuccessListener { documents ->
            for (document in documents) {
                products.add(document.toObject())
            }

            Toast.makeText(context, "Products loaded", Toast.LENGTH_LONG).show()
        }
        .addOnFailureListener { exception ->
            Toast.makeText(context, "Products not loaded", Toast.LENGTH_LONG).show()
        }

    return products
}