package dev.joselogar.corium

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var fa: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*fa = Firebase.auth

        setContent {
            pg(fa)
        }*/
    }
}

@Composable
fun Firebase(fa: FirebaseAuth) {
    Column {
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            text = "Hola"
        )

        var email_login by remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            Alignment.Center
        ) {
            OutlinedTextField(
                value = email_login,
                onValueChange = { onEmailLoginChange -> email_login = onEmailLoginChange },
                label = { Text(text = "email") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                maxLines = 1
            )
        }

        var password_login by remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            Alignment.Center
        ) {
            OutlinedTextField(
                value = password_login,
                onValueChange = { onPasswordLoginChange -> password_login = onPasswordLoginChange },
                label = { Text(text = "password") },
                leadingIcon = { Icon(Icons.Outlined.Email, "email", tint = Color.LightGray) },
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
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Go
                ),
                maxLines = 1
            )
        }

        Button(onClick = {
            fa
                .signInWithEmailAndPassword(email_login, password_login)
                .addOnCompleteListener { //task ->
                    if (it.isSuccessful) {
                        Log.d(TAG, "Todo OK")
                    } else {
                        //Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show()
                        Log.d(TAG, "Meh...")
                    }
                }
        }) {
            Text(text = "Iniciar sesión")
        }

        /*ClickableText(
            text = "Don't have an account? Register",
            onClick = {}
        )*/
    }
}

@Preview(showBackground = true)
@Composable
fun FirebaseDefaultPreview() {
    Firebase(Firebase.auth)
}
