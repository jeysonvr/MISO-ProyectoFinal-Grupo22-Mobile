package proyectofinal.com.example.abc.ui.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import proyectofinal.com.example.abc.R
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import proyectofinal.com.example.abc.ui.utils.TextFieldABC
import proyectofinal.com.example.abc.ui.login.LoginScreen
import proyectofinal.com.example.abc.ui.utils.TextFieldPasswordABC
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun SignUpScreen(navController: NavController) {
    val viewModel = SignUpViewModel()
    val labelFullName = "Full Name"
    val labelEmail = "Email"
    val labelPassword = "Password"
    val fullName: String by viewModel.fullName.observeAsState(initial = "")
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val isRegistered: Boolean by viewModel.isRegistered.observeAsState(initial = false)
    val keyboardController = LocalSoftwareKeyboardController.current
    if (isRegistered) {
        navController.navigate("loginScreen"){
            popUpTo("loginScreen"){
                inclusive = true
            }
        }
    }else{
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.background_login),
                contentDescription = "Background welcome",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {

            LazyColumn(
                modifier = Modifier
                    .matchParentSize()
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {

                    Image(
                        painter = painterResource(id = R.drawable.logo2),
                        contentDescription = "Logo",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(200.dp)
                    )
                }
                item {

                    Text(
                        text = stringResource(id = R.string.register),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 60.dp, bottom = 100.dp),
                        style = TextStyle(
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                            fontSize = 30.sp
                        )
                    )
                }
                item {

                    TextFieldABC(
                        textField = fullName,
                        stringResource(id = R.string.full_name),
                        keyboardController = keyboardController,
                        onTextFieldChanged = { viewModel.onFullNameChanged(it) })
                }
                item {
                    TextFieldABC(
                        textField = email,
                        stringResource(id = R.string.email),
                        keyboardController = keyboardController,
                        onTextFieldChanged = { viewModel.onEmailChanged(it) })
                }
                item {
                    TextFieldPasswordABC(
                        textField = password,
                        stringResource(id = R.string.password),
                        keyboardController = keyboardController,
                        onTextFieldChanged = { viewModel.onPasswordChanged(it) })
                }
                item {
                    Button(
                        onClick = { viewModel.onSignUpClicked() },

                        modifier = Modifier
                            .padding(16.dp, top = 70.dp)
                            .background(Color(0XFF0DA89B)),
                        colors = ButtonDefaults.buttonColors(
                            Color(0XFF0DA89B)
                        ),
                    ) {
                        Text(text = stringResource(id = R.string.register))
                    }
                }
            }
        }
    }

}