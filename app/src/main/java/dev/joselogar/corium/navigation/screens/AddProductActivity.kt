package dev.joselogar.corium.navigation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dev.joselogar.corium.ButtonCorium
import dev.joselogar.corium.CheckboxCorium
import dev.joselogar.corium.OutlinedTextFieldCorium
import dev.joselogar.corium.view.ViewModel

/* AddProductScreen
* navController = NavController manages app navigation within a NavHost.
*/
@Composable
fun AddProductScreen(
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
                    contentDescription = "New product",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            navController.navigate(route = Screens.ProductsScreen.route)
                        },
                    tint = MaterialTheme.colors.onSecondary
                )

                Spacer(
                    modifier = Modifier
                        .width(16.dp)
                )

                Text(
                    text = "New product",
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    ) {
        AddProductBodyContent(
            LocalContext.current,
            navController
        )
    }
}

/* AddProductBodyContent
*       context = Interface to global information about an application environment (toast).
* navController = NavController manages app navigation within a NavHost.
*     viewModel = ViewModel is a class that is responsible for preparing and managing the data for an Activity or a Fragment.
*/
@Composable
fun AddProductBodyContent(
    context: Context,
    navController: NavController,
    viewModel: ViewModel = ViewModel()
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(
                    modifier = Modifier
                        .height(32.dp)
                )

                val valueAvailable = viewModel.valueAvailable.observeAsState(false)
                val valueColorImage = viewModel.valueColorImage.observeAsState("")
                val valueColorName = viewModel.valueColorName.observeAsState("")
                val valueCompany = viewModel.valueCompany.observeAsState("")
                val valueImage = viewModel.valueImage.observeAsState("")
                val valueModel = viewModel.valueModel.observeAsState("")
                val valuePrice = viewModel.valuePrice.observeAsState("")
                val valueYear = viewModel.valueYear.observeAsState("")

                LazyRow(
                    horizontalArrangement = Arrangement.End
                ) {
                    item {
                        CheckboxCorium(
                            valueAvailable.value
                        ) { onValueAvailable ->
                            viewModel.availableOutlinedTextField(
                                onValueAvailable
                            )
                        }

                        Text(
                            text = "Available",
                            modifier = Modifier
                                .padding(
                                    start = 8.dp,
                                    top = 8.dp
                                ),
                            color = MaterialTheme.colors.onSecondary,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )

                OutlinedTextFieldCorium(
                    valueColorImage.value,
                    { onValueColorImage -> viewModel.colorImageOutlinedTextField(onValueColorImage) },
                    "Color image",
                    null,
                    null,
                    KeyboardType.Text,
                    ImeAction.Next,
                    {}
                )

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )

                OutlinedTextFieldCorium(
                    valueColorName.value,
                    { onValueColorName -> viewModel.colorNameOutlinedTextField(onValueColorName) },
                    "Color name",
                    null,
                    null,
                    KeyboardType.Text,
                    ImeAction.Next,
                    {}
                )

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )

                OutlinedTextFieldCorium(
                    valueCompany.value,
                    { onValueCompany -> viewModel.companyOutlinedTextField(onValueCompany) },
                    "Company",
                    null,
                    null,
                    KeyboardType.Text,
                    ImeAction.Next,
                    {}
                )

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )

                OutlinedTextFieldCorium(
                    valueImage.value,
                    { onValueImage -> viewModel.imageOutlinedTextField(onValueImage) },
                    "Image",
                    null,
                    null,
                    KeyboardType.Text,
                    ImeAction.Next,
                    {}
                )

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )

                OutlinedTextFieldCorium(
                    valueModel.value,
                    { onValueModel -> viewModel.modelOutlinedTextField(onValueModel) },
                    "Model",
                    null,
                    null,
                    KeyboardType.Text,
                    ImeAction.Next,
                    {}
                )

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )

                OutlinedTextFieldCorium(
                    valuePrice.value,
                    { onValuePrice -> viewModel.priceOutlinedTextField(onValuePrice) },
                    "Price",
                    null,
                    null,
                    KeyboardType.Number,
                    ImeAction.Next,
                    {}
                )

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )

                OutlinedTextFieldCorium(
                    valueYear.value,
                    { onValueYear -> viewModel.yearOutlinedTextField(onValueYear) },
                    "Year",
                    null,
                    null,
                    KeyboardType.Number,
                    ImeAction.Done,
                    {
                        if (
                            valueColorImage.value.isNotEmpty() &&
                            valueColorName.value.isNotEmpty() &&
                            valueCompany.value.isNotEmpty() &&
                            valueImage.value.isNotEmpty() &&
                            valueModel.value.isNotEmpty() &&
                            valuePrice.value.isNotEmpty() &&
                            valueYear.value.isNotEmpty()
                        )
                            addProduct(
                                valueAvailable.value,
                                listOf(valueColorImage.value),
                                listOf(valueColorName.value),
                                valueCompany.value,
                                listOf(valueImage.value),
                                valueModel.value,
                                valuePrice.value.toDouble(),
                                valueYear.value.toInt(),
                                context,
                                navController
                            )
                        else
                            Toast.makeText(context, "Make sure Fields aren't empty", Toast.LENGTH_LONG).show()
                    }
                )

                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )

                ButtonCorium(
                    onClick = {
                        if (
                            valueColorImage.value.isNotEmpty() &&
                            valueColorName.value.isNotEmpty() &&
                            valueCompany.value.isNotEmpty() &&
                            valueImage.value.isNotEmpty() &&
                            valueModel.value.isNotEmpty() &&
                            valuePrice.value.isNotEmpty() &&
                            valueYear.value.isNotEmpty()
                        )
                            addProduct(
                                valueAvailable.value,
                                listOf(valueColorImage.value),
                                listOf(valueColorName.value),
                                valueCompany.value,
                                listOf(valueImage.value),
                                valueModel.value,
                                valuePrice.value.toDouble(),
                                valueYear.value.toInt(),
                                context,
                                navController
                            )
                        else
                            Toast.makeText(context, "Make sure Fields aren't empty", Toast.LENGTH_LONG).show()
                    },
                    text = "Add",
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.button
                )

                Spacer(
                    modifier = Modifier
                        .height(32.dp)
                )
            }
        }
    }
}

/* addProduct
*       context = Interface to global information about an application environment (toast).
* navController = NavController manages app navigation within a NavHost.
*/
fun addProduct(
    valueAvailable: Boolean,
    valueColorImage: List<String>,
    valueColorName: List<String>,
    valueCompany: String,
    valueImage: List<String>,
    valueModel: String,
    valuePrice: Double,
    valueYear: Int,
    context: Context,
    navController: NavController
) {
    val product = hashMapOf(
        "available" to valueAvailable,
        "colorImage" to valueColorImage,
        "colorName" to valueColorName,
        "company" to valueCompany,
        "image" to valueImage,
        "model" to valueModel,
        "price" to valuePrice,
        "uid" to FirebaseAuth.getInstance().uid,
        "year" to valueYear
    )

    FirebaseFirestore.getInstance()
        .collection("products")
        .add(product)
        .addOnSuccessListener {
            Toast.makeText(context, "Product added", Toast.LENGTH_LONG).show()
            navController.navigate(route = Screens.ProductsScreen.route)
        }
        .addOnFailureListener {
            Toast.makeText(context, "Product not added", Toast.LENGTH_LONG).show()
        }
}