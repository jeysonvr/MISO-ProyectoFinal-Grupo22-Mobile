package proyectofinal.com.example.abc.ui.academic_data

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import proyectofinal.com.example.abc.ui.experience.LaboralExperienceViewModel
import proyectofinal.com.example.abc.ui.utils.SharePreference
import proyectofinal.com.example.abc.R
import proyectofinal.com.example.abc.ui.utils.TextFieldABC
@Preview
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AcademicDataAddScreen(navController: NavController, viewModel: AcademicDataViewModel = hiltViewModel()) {
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
    ) { innerPadding ->
        MainContentAdd(innerPadding, viewModel, keyboardController, navController)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainContentAdd(
    padding: PaddingValues, academicDataViewModel: AcademicDataViewModel,
    keyboardController: SoftwareKeyboardController?,
    navController: NavController
) {

    val instituteEducation: String by academicDataViewModel.instituteEducation.observeAsState(initial = "")
    val carrer: String by academicDataViewModel.carrer.observeAsState(initial = "")
    val startYear: String by academicDataViewModel.startYear.observeAsState(initial = "")
    val finalYear: String by academicDataViewModel.finalYear.observeAsState(initial = "")
    val inProgress : Boolean by academicDataViewModel.inProgress.observeAsState(initial = false)
    val sharePreference = SharePreference(LocalContext.current)
    academicDataViewModel.getInfoUser(sharePreference)
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
                text = stringResource(id = R.string.academic_data),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
        }
        item {
            Spacer(modifier = Modifier
                .padding(bottom = 10.dp)
                .width(200.dp))
            Spacer(modifier = Modifier
                .background(Color.Black)
                .padding(bottom = 1.dp)
                .fillMaxWidth())
            Spacer(modifier = Modifier
                .padding(bottom = 20.dp)
                .width(200.dp))
        }
        item {
            TextFieldABC(
                textField = instituteEducation,
                label = stringResource(id = R.string.institute_education),
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { academicDataViewModel.onInstituteEducationChanged(it) })
        }
        item {
            TextFieldABC(
                textField = carrer,
                label = stringResource(id = R.string.degree),
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { academicDataViewModel.onCarrerChanged(it) })
        }
        item {
            TextFieldABC(
                textField = startYear,
                label = stringResource(id = R.string.start_date),
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { academicDataViewModel.onStartYearChanged(it) })

        }
        item {
            TextFieldABC(
                textField = finalYear,
                label = stringResource(id = R.string.end_date),
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { academicDataViewModel.onFinalYearChanged(it) })
        }

        item {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()) {
                Checkbox(
                    checked = inProgress,
                    onCheckedChange = { newCheckedState ->
                        academicDataViewModel.inProgressChanged(newCheckedState)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.borderText)
                    )
                )
                Text(text = stringResource(id = R.string.in_progress))
            }
        }
        item {
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(end = 20.dp, top = 70.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color.White
                    ),
                    shape = RectangleShape,
                    border = BorderStroke(1.dp, Color.Black),
                ) {
                    Text(text = stringResource(id = R.string.cancel), color = Color.Black)
                }
                Button(
                    onClick = { academicDataViewModel.onSaveClicked {
                        navController.navigate("AcademicDataScreen") {
                            popUpTo("AcademicDataScreen") {
                                inclusive = true
                            }
                        }
                    } },

                    modifier = Modifier
                        .padding(start = 20.dp, top = 70.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color(0XFF0DA89B)
                    ),
                    shape = RectangleShape,
                ) {
                    Text(text = stringResource(id = R.string.save), color = Color.White)
                }
            }
        }

    }
}