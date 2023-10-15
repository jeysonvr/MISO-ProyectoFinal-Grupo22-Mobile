package proyectofinal.com.example.abc.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import proyectofinal.com.example.abc.R

@Preview
@Composable
fun WelcomeScreen() {
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
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(16.dp, top = 70.dp)
                .background(Color(0XFF0DA89B)),
            colors = ButtonDefaults.buttonColors(
                Color(0XFF0DA89B)
            ),
        ) {
            Text(text = "Create account")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(35.dp)
        ) {
            Text(text = "Already have an account? ", modifier = Modifier.padding(top = 10.dp))
            Text(text = "Login ", modifier = Modifier.padding(top = 10.dp, start = 20.dp))
        }
    }
}
