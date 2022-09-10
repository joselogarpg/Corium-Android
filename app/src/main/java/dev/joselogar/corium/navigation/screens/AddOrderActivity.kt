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

/* AddOrderScreen
* navController = NavController manages app navigation within a NavHost.
*/
@Composable
fun AddOrderScreen(
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
                    contentDescription = "New order",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            navController.navigate(route = Screens.OrdersScreen.route)
                        },
                    tint = MaterialTheme.colors.onSecondary
                )

                Spacer(
                    modifier = Modifier
                        .width(16.dp)
                )

                Text(
                    text = "New order",
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    ) {
        AddOrderBodyContent(
            LocalContext.current,
            navController
        )
    }
}

/* AddOrderBodyContent
*       context = Interface to global information about an application environment (toast).
* navController = NavController manages app navigation within a NavHost.
*     viewModel = ViewModel is a class that is responsible for preparing and managing the data for an Activity or a Fragment.
*/
@Composable
fun AddOrderBodyContent(
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

                val valueDate = viewModel.valueDate.observeAsState("")
                val valueInProgress = viewModel.valueInProgress.observeAsState(false)
                val valueModel = viewModel.valueModel.observeAsState("")
                val valueQuantity = viewModel.valueQuantity.observeAsState("")

                LazyRow(
                    horizontalArrangement = Arrangement.End
                ) {
                    item {
                        CheckboxCorium(
                            valueInProgress.value
                        ) { onValueInProgress ->
                            viewModel.inProgressOutlinedTextField(
                                onValueInProgress
                            )
                        }

                        Text(
                            text = "In progress",
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
                    valueDate.value,
                    { onValueDate -> viewModel.dateOutlinedTextField(onValueDate) },
                    "Date",
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
                    valueQuantity.value,
                    { onValueQuantity -> viewModel.quantityOutlinedTextField(onValueQuantity) },
                    "Quantity",
                    null,
                    null,
                    KeyboardType.Number,
                    ImeAction.Done,
                    {
                        if (
                            valueDate.value.isNotEmpty() &&
                            valueModel.value.isNotEmpty() &&
                            valueQuantity.value.isNotEmpty()
                        )
                            addOrder(
                                valueDate.value,
                                valueInProgress.value,
                                valueModel.value,
                                valueQuantity.value.toInt(),
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
                            valueDate.value.isNotEmpty() &&
                            valueModel.value.isNotEmpty() &&
                            valueQuantity.value.isNotEmpty()
                        )
                            addOrder(
                                valueDate.value,
                                valueInProgress.value,
                                valueModel.value,
                                valueQuantity.value.toInt(),
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

/* addOrder
*       context = Interface to global information about an application environment (toast).
* navController = NavController manages app navigation within a NavHost.
*/
fun addOrder(
    valueDate: String,
    valueInProgress: Boolean,
    valueModel: String,
    valueQuantity: Int,
    context: Context,
    navController: NavController
) {
    val order = hashMapOf(
        "date" to valueDate,
        "inProgress" to valueInProgress,
        "model" to valueModel,
        "quantity" to valueQuantity,
        "uid" to FirebaseAuth.getInstance().uid
    )

    FirebaseFirestore.getInstance()
        .collection("orders")
        .add(order)
        .addOnSuccessListener {
            Toast.makeText(context, "Order added", Toast.LENGTH_LONG).show()
            navController.navigate(route = Screens.OrdersScreen.route)
        }
        .addOnFailureListener {
            Toast.makeText(context, "Order not added", Toast.LENGTH_LONG).show()
        }
}