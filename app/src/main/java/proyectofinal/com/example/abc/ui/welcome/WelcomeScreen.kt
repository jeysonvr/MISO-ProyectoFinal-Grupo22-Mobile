package proyectofinal.com.example.abc.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import proyectofinal.com.example.abc.R
import proyectofinal.com.example.abc.ui.login.LoginScreen
import proyectofinal.com.example.abc.ui.sign_up.SignUpScreen
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink



@Composable
fun WelcomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_welcome),
            contentDescription = "Background welcome",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo2),
            contentDescription = "Logo",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(300.dp)
        )
        Text(text = "")
        Text(text = "")
        Button(
            onClick = { navController.navigate("SignUpScreen") },
            modifier = Modifier
                .padding(16.dp, top = 70.dp)
                .background(Color(0XFF0DA89B)),
            colors = ButtonDefaults.buttonColors(
                Color(0XFF0DA89B)
            ),
        ) {
            Text(text = stringResource(id = R.string.sign_up))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(35.dp)
        ) {
            Text(text = stringResource(id = R.string.already_account), modifier = Modifier.padding(top = 10.dp))
            Text(text = stringResource(id = R.string.login),
                modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp)
                    .clickable { navController.navigate("loginScreen") })
        }
    }
}
