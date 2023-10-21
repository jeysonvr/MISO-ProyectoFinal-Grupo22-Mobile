package proyectofinal.com.example.abc.ui.experience

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import proyectofinal.com.example.abc.R
import proyectofinal.com.example.abc.ui.academic_data.AcademicDataViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Preview
@Composable
fun LaboralExperienceScreen() {
    val laboralExperienceViewModel = LaboralExperienceViewModel()


    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val keyboardController = LocalSoftwareKeyboardController.current
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
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Add a new academic data */ },
                containerColor = colorResource(id = R.color.colorButtodAdd),
                shape = CircleShape,
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add",
                    tint = colorResource(id = R.color.colorButtonPlus)
                )
            }
        }
    ) { innerPadding ->
        MainContent(innerPadding, laboralExperienceViewModel, keyboardController)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainContent(
    padding: PaddingValues, laboralExperienceViewModel: LaboralExperienceViewModel,
    keyboardController: SoftwareKeyboardController?
) {
    LazyColumn(
        modifier = Modifier.padding(
            top = 96.dp,
            start = 30.dp,
            end = 30.dp,
            bottom = padding.calculateBottomPadding()
        )
    )
    {
        item {
            Text(
                text = "Work experience",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .width(200.dp)
            )
            Spacer(
                modifier = Modifier
                    .background(Color.Black)
                    .padding(bottom = 1.dp)
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .width(200.dp)
            )
        }
        item {
            itemAcademicData()
        }


    }
}

@Preview
@Composable
fun itemAcademicData() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column {

            Row(modifier = Modifier) {
                Image(
                    painter = painterResource(id = R.drawable.img_work),
                    contentDescription = "image_work",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(100.dp)
                        .weight(1.5f)
                )
                ConstraintLayout(
                    modifier = Modifier
                        .height(100.dp)
                        .weight(3f)
                ) {
                    val (name, carrer, time) = createRefs()
                    Text(text = "Android Developer", modifier = Modifier
                        .constrainAs(name) {
                            top.linkTo(parent.top, margin = 2.dp)
                            start.linkTo(parent.start, margin = 20.dp)
                        }
                        .fillMaxWidth(1f), fontWeight = FontWeight.Bold)
                    Row(modifier = Modifier
                        .constrainAs(carrer) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start, margin = 20.dp)
                        }
                        .fillMaxWidth(1f)) {
                        Text(text = "Empresa", modifier = Modifier.fillMaxWidth())
                    }
                    Row(modifier = Modifier
                        .constrainAs(time) {
                            bottom.linkTo(parent.bottom, margin = 2.dp)
                            start.linkTo(parent.start, margin = 20.dp)
                        }
                        .fillMaxWidth(1f)) {
                        Text(text = "2022 - 2024", modifier = Modifier.fillMaxWidth())
                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.outline_delete_24),
                    contentDescription = "delete",
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .width(200.dp)
            )
            Spacer(
                modifier = Modifier
                    .background(Color.Black)
                    .padding(bottom = 1.dp)
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .width(200.dp)
            )
        }
    }
}