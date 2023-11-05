package proyectofinal.com.example.abc.ui.experience

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import proyectofinal.com.example.abc.R
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import proyectofinal.com.example.abc.ui.utils.ComboOption
import proyectofinal.com.example.abc.ui.utils.SharePreference
import proyectofinal.com.example.abc.ui.utils.SingleComboBox
import proyectofinal.com.example.abc.ui.academic_data.AcademicDataViewModel
import proyectofinal.com.example.abc.ui.utils.TextFieldABC

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LaboralExperienceAddScreen(navController: NavController,viewModel: LaboralExperienceViewModel = hiltViewModel()) {
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
    padding: PaddingValues, laboralExperienceViewModel: LaboralExperienceViewModel,
    keyboardController: SoftwareKeyboardController?, navController: NavController
) {
    val companyName: String by laboralExperienceViewModel.companyName.observeAsState(initial = "")
    val startYear: String by laboralExperienceViewModel.startDate.observeAsState(initial = "")
    val finalYear: String by laboralExperienceViewModel.finalDate.observeAsState(initial = "")
    val description: String by laboralExperienceViewModel.description.observeAsState(initial = "")
    val workHere : Boolean by laboralExperienceViewModel.workHere.observeAsState(initial = false)
    val roles : List<ComboOption> by laboralExperienceViewModel.roles.observeAsState(initial = listOf())
    val rolSelected: List<ComboOption> by laboralExperienceViewModel.rolSelected.observeAsState(
        initial = listOf()
    )
    val sharePreference = SharePreference(LocalContext.current)
    laboralExperienceViewModel.getMetaData(sharePreference = sharePreference)
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
                text = stringResource(id = R.string.work_experience),
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
                textField = companyName,
                label = stringResource(id = R.string.company_name),
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { laboralExperienceViewModel.onCompanyNameChanged(it) })
        }
        item {
            SingleComboBox(
                options = roles,
                selectedIds = rolSelected.map { it.id },
                labelText = stringResource(id = R.string.rol),
                modifier = Modifier.fillMaxWidth(),
                onOptionsChosen = { laboralExperienceViewModel.onRolChanged(it) })
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()) {
                Checkbox(
                    checked = workHere,
                    onCheckedChange = { newCheckedState ->
                        laboralExperienceViewModel.onWorkHere(newCheckedState)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.borderText)
                    )
                )
                Text(text = stringResource(id = R.string.working))
            }
        }
        item {
            TextFieldABC(
                textField = startYear,
                label = stringResource(id = R.string.start_date),
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { laboralExperienceViewModel.onStartDateChanged(it) })

        }
        item {
            TextFieldABC(
                textField = finalYear,
                label = stringResource(id = R.string.end_date),
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                isEditable = !workHere,
                onTextFieldChanged = { laboralExperienceViewModel.onFinalDateChanged(it) })
        }
        item {
            TextFieldABC(
                textField = description,
                label = stringResource(id = R.string.description),
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth().height(200.dp),
                onTextFieldChanged = { laboralExperienceViewModel.onDescriptionChanged(it) })
        }


        item {
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = { navController.popBackStack()},
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
                    onClick = { laboralExperienceViewModel.onSave(
                        onSaveSuccess = {
                            navController.navigate("LaboralExperienceScreen") {
                                popUpTo("LaboralExperienceScreen") {
                                    inclusive = true
                                }
                            }
                        }
                    ) },

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