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
import proyectofinal.com.example.abc.ui.academic_data.AcademicDataViewModel
import proyectofinal.com.example.abc.ui.utils.TextFieldABC

@Preview
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LaboralExperienceAddScreen() {
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
    ) { innerPadding ->
        MainContentAdd(innerPadding, laboralExperienceViewModel, keyboardController)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainContentAdd(
    padding: PaddingValues, laboralExperienceViewModel: LaboralExperienceViewModel,
    keyboardController: SoftwareKeyboardController?
) {
    val labelCompany = "Company name"
    val labelRol = "Rol"
    val labelActuallyWork = "Actually work here"
    val labelStartYear = "Start Year"
    val labelFinalYear = "Final Year"
    val labelLaboralExperience = "Laboral Experience"
    val labelDescription = "Description about your experience"


    val companyName: String by laboralExperienceViewModel.companyName.observeAsState(initial = "")
    val rol: String by laboralExperienceViewModel.rol.observeAsState(initial = "")
    val startYear: String by laboralExperienceViewModel.startYear.observeAsState(initial = "")
    val finalYear: String by laboralExperienceViewModel.finalYear.observeAsState(initial = "")
    val description: String by laboralExperienceViewModel.description.observeAsState(initial = "")
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
                text = labelLaboralExperience,
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
                label = labelCompany,
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { laboralExperienceViewModel.onCompanyNameChanged(it) })
        }
        item {
            TextFieldABC(
                textField = rol,
                label = labelRol,
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { laboralExperienceViewModel.onRolChanged(it) })
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()) {
                Checkbox(
                    checked = true,
                    onCheckedChange = { newCheckedState ->
                        laboralExperienceViewModel.onWorkHere(newCheckedState)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.borderText)
                    )
                )
                Text(text = labelActuallyWork)
            }
        }
        item {
            TextFieldABC(
                textField = startYear,
                label = labelStartYear,
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { laboralExperienceViewModel.onStartYearChanged(it) })

        }
        item {
            TextFieldABC(
                textField = finalYear,
                label = labelFinalYear,
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { laboralExperienceViewModel.onFinalYearChanged(it) })
        }
        item {
            TextFieldABC(
                textField = description,
                label = labelDescription,
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth().height(200.dp),
                onTextFieldChanged = { laboralExperienceViewModel.onCompanyNameChanged(it) })
        }


        item {
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(end = 20.dp, top = 70.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color.White
                    ),
                    shape = RectangleShape,
                    border = BorderStroke(1.dp, Color.Black),
                ) {
                    Text(text = "Cancelar", color = Color.Black)
                }
                Button(
                    onClick = { /*TODO*/ },

                    modifier = Modifier
                        .padding(start = 20.dp, top = 70.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color(0XFF0DA89B)
                    ),
                    shape = RectangleShape,
                ) {
                    Text(text = "Guardar", color = Color.White)
                }
            }
        }

    }
}