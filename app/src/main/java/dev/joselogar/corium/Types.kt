package dev.joselogar.corium

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.runtime.*
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun LoginSignupEmailOutlinedTextField(email_login: String) {
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
}

@Composable
fun LoginSignupPasswordOutlinedTextField(password_login: String) {
    val fm = LocalFocusManager.current
    var password = password_login

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
}