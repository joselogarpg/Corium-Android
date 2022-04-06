package dev.joselogar.corium

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth

//private lateinit var fa: FirebaseAuth
private var fa = FirebaseAuth.getInstance()
//fa = Firebase.auth

@Composable
fun SignupScreen(navController: NavHostController) {
    Scaffold {
        SignupBodyContent(navController)
    }
}

@Composable
fun SignupBodyContent(navController: NavController) {
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
        Image(
            painter = painterResource(id = R.drawable.corium),
            contentDescription = "Corium",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 64.dp)
                .size(125.dp),
        )

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "SIGN UP",
            style = MaterialTheme.typography.h3
        )
        Text(
            text = "to continue",
            style = MaterialTheme.typography.h5
        )
        
        Card(
            modifier = Modifier.padding(top = 32.dp),
            /* shadow below the card */
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color.Transparent
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val fm = LocalFocusManager.current

                var email by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = email,
                    onValueChange = { onEmailLoginChange ->
                        email = onEmailLoginChange
                    },
                    label = { Text(text = "email") },
                    leadingIcon = { Icon(Icons.Outlined.Email, "email", tint = Color.LightGray) },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.LightGray,
                        disabledTextColor = Color.DarkGray,
                        leadingIconColor = Color.Green,
                        backgroundColor = MaterialTheme.colors.background,
                        cursorColor = Color.DarkGray,
                        focusedIndicatorColor = Color.DarkGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        placeholderColor = Color.Green
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { fm.moveFocus(FocusDirection.Down) }),
                    maxLines = 1
                )

                var password by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = password,
                    onValueChange = { onPasswordLoginChange ->
                        password = onPasswordLoginChange
                    },
                    label = { Text(text = "password") },
                    leadingIcon = { Icon(Icons.Outlined.Visibility, "email", tint = Color.LightGray) },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.LightGray,
                        disabledTextColor = Color.DarkGray,
                        leadingIconColor = Color.Green,
                        backgroundColor = Color.White,
                        cursorColor = Color.DarkGray,
                        focusedIndicatorColor = Color.DarkGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        placeholderColor = Color.Green
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Go
                    ),
                    keyboardActions = KeyboardActions(onNext = { fm.clearFocus() }),
                    maxLines = 1
                )

                Button(
                    modifier = Modifier.padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary),
                    onClick = {
                    fa
                        .createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { //task ->
                            if (it.isSuccessful) {
                                navController.navigate(route = Screens.HomeScreen.route)
                                Log.d(TAG, "createUserWithEmailAndPassword:success")
                            } else {
                                //Toast.makeText(this, "Message", Toast.LENGTH_SHORT).show()
                                Log.d(TAG, "createUserWithEmailAndPassword:failure")
                            }
                        }
                }) {
                    Text(
                        text = "Sign Up",
                        style = MaterialTheme.typography.button
                    )
                }
            }
        }

        Row {
            Text(
                modifier = Modifier.padding(top = 32.dp, end = 8.dp),
                text = "Have an account?",
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .clickable { navController.navigate(route = Screens.LoginScreen.route) },
                text = "Log In",
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupDefaultPreview() {
    SignupBodyContent(navController = rememberNavController())
}