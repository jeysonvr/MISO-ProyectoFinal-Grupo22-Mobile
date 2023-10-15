package proyectofinal.com.example.abc.ui.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import proyectofinal.com.example.abc.R


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TextFieldABC(
    textField: String,
    label: String,
    keyboardController: SoftwareKeyboardController?,
    modifier: Modifier = Modifier,
    onTextFieldChanged: (String) -> Unit
) {
    TextField(
        value = textField,
        onValueChange = { onTextFieldChanged(it) },
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