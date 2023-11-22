package proyectofinal.com.example.abc.ui.technic_test

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import proyectofinal.com.example.abc.R
import proyectofinal.com.example.abc.model.TestResponseDTOItem
import proyectofinal.com.example.abc.ui.utils.SharePreference


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TechnicTestScreen(
    navController: NavController,
    viewModel: TechnicTestViewModel = hiltViewModel()
) {


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
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
        }
    ) { innerPadding ->
        MainContent(navController, innerPadding, viewModel, keyboardController)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun MainContent(
    navController: NavController,
    padding: PaddingValues,
    technicTestViewModel: TechnicTestViewModel,
    keyboardController: SoftwareKeyboardController?
) {
    val sharePreference = SharePreference(LocalContext.current)
    val listTests: List<TestResponseDTOItem>? by technicTestViewModel.listTests!!.observeAsState(
        initial = listOf()
    )
    technicTestViewModel.getInfoUser(sharePreference = sharePreference)
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
                text = stringResource(id = R.string.tecnic_test),
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
        if (listTests?.isNotEmpty() == true) {
            listTests!!.forEach {
                item {
                    itemTest(
                        it.nombre_evaluacion,
                        it.estado,
                        it.calificacion,
                        it.id,
                        navController
                    )
                }
            }
        } else {
            item {
                Text(
                    text = stringResource(id = R.string.no_tecnical_test),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )
            }
        }


    }
}


@Composable
private fun itemTest(
    name: String?,
    state: String?,
    score: String?,
    idTest: Int,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {

            Row(modifier = Modifier.clickable {
                if (state.equals("Pendiente")) {
                    navController.navigate("DoingTestScreen/$idTest")
                }
            }) {
                Image(
                    painter = painterResource(id = R.drawable.technical_test_img),
                    contentDescription = "image_academic",
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
                    val (nameConstraint, stateConstraint, scoreConstraint) = createRefs()
                    Text(text = name ?: " ", modifier = Modifier
                        .constrainAs(nameConstraint) {
                            top.linkTo(parent.top, margin = 2.dp)
                            start.linkTo(parent.start, margin = 20.dp)
                        }
                        .fillMaxWidth(1f),
                        fontWeight = FontWeight.Bold)
                    Row(modifier = Modifier
                        .constrainAs(stateConstraint) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start, margin = 20.dp)
                        }
                        .fillMaxWidth(1f)) {
                        Text(text = state ?: " ", modifier = Modifier.fillMaxWidth())
                    }
                    Row(modifier = Modifier
                        .constrainAs(scoreConstraint) {
                            bottom.linkTo(parent.bottom, margin = 2.dp)
                            start.linkTo(parent.start, margin = 20.dp)
                        }
                        .fillMaxWidth(1f)) {
                        Text(text = score ?: " ", modifier = Modifier.fillMaxWidth())
                    }
                }
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