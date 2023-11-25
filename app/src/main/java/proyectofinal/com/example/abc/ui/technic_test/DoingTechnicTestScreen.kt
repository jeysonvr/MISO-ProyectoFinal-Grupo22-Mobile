package proyectofinal.com.example.abc.ui.technic_test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import proyectofinal.com.example.abc.R
import proyectofinal.com.example.abc.model.PosiblesRespuesta
import proyectofinal.com.example.abc.ui.utils.SharePreference
import proyectofinal.com.example.abc.ui.utils.mToast

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun DoingTechnicTestScreen(idTest: Int,
                           navController: NavController,
                           viewModel: TechnicTestViewModel = hiltViewModel()) {


    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val keyboardController = LocalSoftwareKeyboardController.current
    val mContext = LocalContext.current
    LaunchedEffect(idTest) {
        viewModel.startTest(idTest, onSaveSuccess = { navController.popBackStack() }, onSaveFailed = {
            mToast(
                context = mContext,
                message = mContext.getString(R.string.error_generic)
            )} )
    }
    //viewModel.startTest(idTest, onSaveSuccess = { navController.popBackStack()})
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
        MainContent(innerPadding, viewModel, keyboardController,navController)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun MainContent(
    padding: PaddingValues, technicTestViewModel: TechnicTestViewModel,
    keyboardController: SoftwareKeyboardController?,
    navController: NavController,
) {
    val mContext = LocalContext.current
    val sharePreference = SharePreference(mContext)
    val questionOutDTO by technicTestViewModel.question!!.observeAsState( initial = null)
    val buttonLabel by technicTestViewModel.buttonLabel.observeAsState(initial = "")

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
        item {
            itemQuestion(titulo = questionOutDTO?.pregunta,
                options = questionOutDTO?.posibles_respuestas,
                technicTestViewModel = technicTestViewModel)
        }

        item{
            Button(
                onClick = { technicTestViewModel.onSaveAnswerClicked(onSaveSuccess = {
                    mToast(
                        context = mContext,
                        message = mContext.getString(R.string.test_completed)
                    )
                    navController.popBackStack()
                },onSaveFailed = {
                    mToast(
                        context = mContext,
                        message = mContext.getString(R.string.error_generic)
                    )
                }
                ) },

                modifier = Modifier
                    .padding(start = 20.dp, top = 70.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(0XFF0DA89B)
                ),
                shape = RectangleShape,
            ) {
                Text(text = buttonLabel, color = Color.White)
            }
        }

    }
}
@Composable
private fun itemQuestion(titulo: String?,
                         options: List<PosiblesRespuesta>?,
                         technicTestViewModel: TechnicTestViewModel) {
    val selectedOption by technicTestViewModel.selectedOption.observeAsState()
    Box(modifier = Modifier.fillMaxWidth()) {
        Column {
            Text(
                text = titulo ?: " ",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
            if (!options.isNullOrEmpty()) {
                options?.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = option == selectedOption,
                            onClick = { technicTestViewModel.setSelectedOption(option) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colorScheme.primary,
                                unselectedColor = MaterialTheme.colorScheme.primary
                            )
                        )
                        Text(
                            text = option.respuesta,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
            }
        }
    }
}