package proyectofinal.com.example.abc.ui.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import proyectofinal.com.example.abc.R


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TextFieldABC(
    textField: String,
    label: String,
    keyboardController: SoftwareKeyboardController?,
    modifier: Modifier = Modifier,
    isEditable: Boolean = true,
    onTextFieldChanged: (String) -> Unit
) {
    TextField(
        value = textField,
        onValueChange = { onTextFieldChanged(it) },
        readOnly = !isEditable,
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hideSoftwareKeyboard() }),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            textColor = Color.Black,
            cursorColor = Color(0XFF0DA89B),
            focusedIndicatorColor = Color(0XFF0DA89B),
            unfocusedIndicatorColor = Color(0XFF0DA89B),
            disabledIndicatorColor = Color(0XFF0DA89B),
        ),
        label = { Text(text = label, color = Color.Black) },

        modifier = modifier.background(Color.White)
            .padding(top = 10.dp,bottom = 10.dp)
            .border(BorderStroke(1.dp, colorResource(id = R.color.borderText))),
    )
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TextFieldPasswordABC(
    textField: String,
    label: String,
    keyboardController: SoftwareKeyboardController?,
    modifier: Modifier = Modifier,
    onTextFieldChanged: (String) -> Unit
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    TextField(
        value = textField,
        onValueChange = { onTextFieldChanged(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            textColor = Color.Black,
            cursorColor = Color(0XFF0DA89B),
            focusedIndicatorColor = Color(0XFF0DA89B),
            unfocusedIndicatorColor = Color(0XFF0DA89B),
            disabledIndicatorColor = Color(0XFF0DA89B),
        ),
        label = { Text(text = label, color = Color.Black) },

        modifier = modifier.background(Color.White)
            .padding(top = 10.dp,bottom = 10.dp)
            .border(BorderStroke(1.dp, colorResource(id = R.color.borderText))),
    )
}