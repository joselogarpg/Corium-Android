package dev.joselogar.corium

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth

private lateinit var fa: FirebaseAuth
//fa = Firebase.auth

@Composable
fun LoginScreen(navController: NavHostController) {
    Scaffold {
        LoginBodyContent(navController)
    }
}

@Composable
fun LoginBodyContent(navController: NavController) {
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
            text = "LOG IN",
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
                var email_login by remember { mutableStateOf("") }
                LoginSignupEmailOutlinedTextField(email_login)

                var password_login by remember { mutableStateOf("") }
                LoginSignupPasswordOutlinedTextField(password_login)

                Text(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.End)
                        .clickable { /* TODO */ },
                    text = "Forgot password?",
                    style = MaterialTheme.typography.subtitle1
                )

                Button(
                    modifier = Modifier.padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary),
                    onClick = {
                    fa
                        .signInWithEmailAndPassword(email_login, password_login)
                        .addOnCompleteListener { //task ->
                            if (it.isSuccessful) {
                                navController.navigate(route = Screens.HomeScreen.route)
                            } else {
                                //Toast.makeText(this, "Message", Toast.LENGTH_SHORT).show()
                                Log.d(TAG, "Message...")
                            }
                        }
                }) {
                    Text(
                        text = "Log In",
                        style = MaterialTheme.typography.button
                    )
                }
            }
        }

        Row {
            Text(
                modifier = Modifier.padding(top = 32.dp, end = 8.dp),
                text = "Don't have an account?",
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .clickable { navController.navigate(route = Screens.SignupScreen.route) },
                text = "Sign Up",
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginDefaultPreview() {
    LoginBodyContent(navController = rememberNavController())
}