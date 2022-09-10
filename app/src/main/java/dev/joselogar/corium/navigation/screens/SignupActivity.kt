package dev.joselogar.corium.navigation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import dev.joselogar.corium.ButtonCorium
import dev.joselogar.corium.OutlinedTextFieldCorium
import dev.joselogar.corium.R
import dev.joselogar.corium.view.ViewModel

/* SignupScreen
* navController = NavController manages app navigation within a NavHost.
*/
@Composable
fun SignupScreen(
    navController: NavController
) {
    Scaffold {
        SignupBodyContent(
            LocalContext.current,
            navController
        )
    }
}

/* SignupBodyContent
*       context = Interface to global information about an application environment (toast).
* navController = NavController manages app navigation within a NavHost.
*     viewModel = ViewModel is a class that is responsible for preparing and managing the data for an Activity or a Fragment.
*/
@Composable
fun SignupBodyContent(
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(
                    modifier = Modifier
                        .height(64.dp)
                )

                Image(
                    painter = painterResource(
                        id = R.drawable.corium_black
                    ),
                    contentDescription = "Corium",
                    modifier = Modifier
                        .size(125.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(
                    modifier = Modifier
                        .height(32.dp)
                )

                Text(
                    text = "Sign Up",
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.h2
                )

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )

                Text(
                    text = "to continue",
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.h6
                )

                Spacer(
                    modifier = Modifier
                        .height(32.dp)
                )

                val valueEmail = viewModel.valueEmail.observeAsState("")
                val valuePassword = viewModel.valuePassword.observeAsState("")
                val valuePasswordTwin = viewModel.valuePasswordTwin.observeAsState("")

                OutlinedTextFieldCorium(
                    valueEmail.value,
                    { onValueEmail -> viewModel.emailOutlinedTextField(onValueEmail) },
                    "Email",
                    Icons.Outlined.Email,
                    "Email",
                    KeyboardType.Email,
                    ImeAction.Next,
                    {}
                )

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )

                OutlinedTextFieldCorium(
                    valuePassword.value,
                    { onValuePassword -> viewModel.passwordOutlinedTextField(onValuePassword) },
                    "Password",
                    Icons.Outlined.Visibility,
                    null,
                    KeyboardType.Password,
                    ImeAction.Next,
                    {}
                )

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )

                OutlinedTextFieldCorium(
                    valuePasswordTwin.value,
                    { onValuePasswordTwin -> viewModel.passwordTwinOutlinedTextField(onValuePasswordTwin) },
                    "Password",
                    Icons.Outlined.Visibility,
                    null,
                    KeyboardType.Password,
                    ImeAction.Done,
                    {
                        if (
                            valueEmail.value.isNotEmpty() &&
                            valuePassword.value.isNotEmpty() &&
                            valuePasswordTwin.value.isNotEmpty()
                        )
                            if (valuePassword.value == valuePasswordTwin.value)
                                createUserWithEmailAndPassword(
                                    valueEmail.value,
                                    valuePassword.value,
                                    context,
                                    navController
                                )
                            else
                                Toast.makeText(context, "Passwords don't match", Toast.LENGTH_LONG).show()
                        else
                            Toast.makeText(context, "Make sure Email and Password aren't empty", Toast.LENGTH_LONG).show()
                    }
                )

                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )

                ButtonCorium(
                    onClick = {
                        if (
                            valueEmail.value.isNotEmpty() &&
                            valuePassword.value.isNotEmpty() &&
                            valuePasswordTwin.value.isNotEmpty()
                        )
                            if (valuePassword.value == valuePasswordTwin.value)
                                createUserWithEmailAndPassword(
                                    valueEmail.value,
                                    valuePassword.value,
                                    context,
                                    navController
                                )
                            else
                                Toast.makeText(context, "Passwords don't match", Toast.LENGTH_LONG).show()
                        else
                            Toast.makeText(context, "Make sure Email and Password aren't empty", Toast.LENGTH_LONG).show()
                    },
                    text = "Sign Up",
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.button
                )

                Spacer(
                    modifier = Modifier
                        .height(32.dp)
                )

                LazyRow {
                    item {
                        Text(
                            text = "Do you have account?",
                            color = MaterialTheme.colors.onSecondary,
                            style = MaterialTheme.typography.subtitle2
                        )

                        Spacer(
                            modifier = Modifier
                                .width(8.dp)
                        )

                        Text(
                            text = "Log In",
                            modifier = Modifier
                                .padding(
                                    end = 8.dp
                                )
                                .clickable {
                                    navController.navigate(route = Screens.LoginScreen.route)
                                },
                            color = MaterialTheme.colors.onSecondary,
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .height(64.dp)
                )
            }
        }
    }
}

/* createUserWithEmailAndPassword
*       context = Interface to global information about an application environment (toast).
* navController = NavController manages app navigation within a NavHost.
*/
fun createUserWithEmailAndPassword(
    valueEmail: String,
    valuePassword: String,
    context: Context,
    navController: NavController
) {
    FirebaseAuth.getInstance()
        .createUserWithEmailAndPassword(valueEmail, valuePassword)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Signup successful", Toast.LENGTH_LONG).show()
                navController.navigate(route = Screens.LoginScreen.route)
            }
            else
                Toast.makeText(context, "Signup failure", Toast.LENGTH_LONG).show()
        }
        .addOnFailureListener {
            Toast.makeText(context, "Database access failure", Toast.LENGTH_LONG).show()
        }
}