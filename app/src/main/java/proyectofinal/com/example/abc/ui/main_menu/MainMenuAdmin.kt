package proyectofinal.com.example.abc.ui.main_menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import proyectofinal.com.example.abc.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuAdminScreen(navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = colorResource(id = R.color.borderText),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                },
                actions = {
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        MainContent(innerPadding, navController)
    }
}

@Composable
private fun MainContent(padding: PaddingValues, navController: NavController) {
    Column(
        modifier = Modifier.padding(
            top = 96.dp,
            start = 30.dp,
            end = 30.dp,
            bottom = padding.calculateBottomPadding()
        )
    ) {
        Text(
            text = stringResource(id = R.string.hello),
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )
        LazyColumn() {
            item {
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .fillMaxSize()
                            .weight(1f)
                            .clickable { navController.navigate("NewContractScreen") }
                    ) {
                        val (image, contentDescription) = createRefs()

                        Image(
                            painter = painterResource(id = R.drawable.background_profile),
                            contentDescription = "Logo",

                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .constrainAs(image) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                                .fillMaxSize()

                        )
                        Text(text = stringResource(id = R.string.new_contract),textAlign = TextAlign.Center, modifier = Modifier.constrainAs(contentDescription) {
                            top.linkTo(parent.top, margin = 100.dp)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        })
                    }
                    ConstraintLayout(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxSize()
                            .weight(1f)
                            .clickable { navController.navigate("InterviewScreen") }
                    ) {
                        val (image, contentDescription) = createRefs()

                        Image(
                            painter = painterResource(id = R.drawable.background_interview),
                            contentDescription = "Logo",

                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .constrainAs(image) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                                .fillMaxSize()
                        )
                        Text(text = stringResource(id = R.string.interviews),textAlign = TextAlign.Center, modifier = Modifier.constrainAs(contentDescription) {
                            top.linkTo(parent.top, margin = 100.dp)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        })
                    }
                }
            }
        }
    }

}