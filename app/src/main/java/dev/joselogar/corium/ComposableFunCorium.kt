package dev.joselogar.corium

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

fun toastMessage(
    context: Context,
    message: String
) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT
    ).show()
}

/* OutlinedTextFieldCorium
*                text = The text to be displayed (label).
*        [imageVector = Vector graphics object that is generated as a result of ImageVector (icon).]
* [contentDescription = Text used by accessibility services to describe what this icon represents (icon description.]
*        keyboardType = Values representing the different available Keyboard Types.
*           imeAction = Signals the keyboard what type of action should be displayed.
*     keyboardActions = The KeyboardActions class allows developers to specify actions that will be triggered in response to users triggering IME action on the software keyboard.
*/
@Composable
fun OutlinedTextFieldCorium(
    value: String,
    onValue: (String) -> Unit,
    text: String,
    imageVector: ImageVector?,
    contentDescription: String?,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    keyboardActions: (KeyboardActionScope) -> Unit
) {
    var valueHidden by remember { mutableStateOf(true) }

    OutlinedTextField(
        value = value,
        onValueChange = onValue,
        label = {
            Text(
                text = text,
                style = MaterialTheme.typography.body2
            )
        },
        leadingIcon = {
            if (imageVector == Icons.Outlined.Visibility)
                IconButton(
                    onClick = { valueHidden = !valueHidden }
                ) {
                    val visibilityOff =
                        if (!valueHidden)
                            Icons.Outlined.VisibilityOff
                        else
                            Icons.Outlined.Visibility

                    val tint =
                        if (!valueHidden)
                            MaterialTheme.colors.error
                        else
                            MaterialTheme.colors.onSecondary

                    val contentDescription =
                        if (!valueHidden)
                            "Show password"
                        else
                            "Hide password"

                    Icon(
                        imageVector = visibilityOff,
                        contentDescription = contentDescription,
                        tint = tint
                    )
                }
            else
                imageVector?.let {
                    Icon(
                        it,
                        contentDescription = contentDescription,
                        tint = MaterialTheme.colors.onSecondary
                    )
                }
        },
        visualTransformation =
            if (imageVector == Icons.Outlined.Visibility && valueHidden)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            keyboardActions
        ),
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onSecondary,
            cursorColor = MaterialTheme.colors.onSecondary,
            focusedIndicatorColor = MaterialTheme.colors.onSecondary, // Border focus
            unfocusedIndicatorColor = MaterialTheme.colors.onSecondary, // Border not focus
            focusedLabelColor = MaterialTheme.colors.onSecondary,
            unfocusedLabelColor = MaterialTheme.colors.onSecondary,
            backgroundColor = MaterialTheme.colors.background
        )
    )
}

@Composable
fun CheckboxCorium(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = Modifier
            .size(32.dp)
    )
}

@Composable
fun ButtonCorium(
    onClick: () -> Unit,
    text: String,
    color: Color,
    style: TextStyle
) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.large
    ) {
        TextCorium(
            text = text,
            color = color,
            style = style
        )
    }
}

@Composable
fun TextCorium(
    text: String,
    color: Color,
    style: TextStyle
) {
    Text(
        text = text,
        color = color,
        style = style
    )
}