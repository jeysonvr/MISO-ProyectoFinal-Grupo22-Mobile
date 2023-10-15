package proyectofinal.com.example.abc.ui.personal_data

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import proyectofinal.com.example.abc.R
import proyectofinal.com.example.abc.ui.utils.ComboOption
import proyectofinal.com.example.abc.ui.utils.MultiComboBox
import proyectofinal.com.example.abc.ui.utils.SingleComboBox
import proyectofinal.com.example.abc.ui.utils.TextFieldABC

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Preview
@Composable
fun PersonalDataScreen() {
    val personalDataViewModel = PersonalDataViewModel()



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
        MainContent(innerPadding, personalDataViewModel, keyboardController)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainContent(
    padding: PaddingValues, personalDataViewModel: PersonalDataViewModel,
    keyboardController: SoftwareKeyboardController?
) {
    val labelName = "Name"
    val labelEmail = "Email"
    val labelAge = "Age"
    val labelPhone = "Phone"
    val labelLanguages = "Languages"
    val labelSoftSkills = "Soft Skills"
    val labelCountry = "Country"

    val email: String by personalDataViewModel.email.observeAsState(initial = "")
    val name: String by personalDataViewModel.fullName.observeAsState(initial = "")
    val age: String by personalDataViewModel.edad.observeAsState(initial = "")
    val phone: String by personalDataViewModel.phone.observeAsState(initial = "")
    val country: List<ComboOption> by personalDataViewModel.paises.observeAsState(initial = listOf())
    val languages: List<ComboOption> by personalDataViewModel.languages.observeAsState(initial = listOf())
    val softSkills: List<ComboOption> by personalDataViewModel.softSkills.observeAsState(initial = listOf())
    val listadoPaises: List<ComboOption> = listOf<ComboOption>(
        ComboOption("Colombia", 1),
        ComboOption("Peru", 2),
        ComboOption("Ecuador", 3)
    )
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
                text = "Personal Data",
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
                textField = name,
                label = labelName,
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { personalDataViewModel.onFullNameChanged(it) })
        }
        item {
            TextFieldABC(
                textField = age,
                label = labelAge,
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { personalDataViewModel.onFullNameChanged(it) })
        }
        item {
            TextFieldABC(
                textField = email,
                label = labelEmail,
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { personalDataViewModel.onFullNameChanged(it) })

        }
        item {
            TextFieldABC(
                textField = phone,
                label = labelPhone,
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { personalDataViewModel.onFullNameChanged(it) })
        }

        item {
            SingleComboBox(
                labelText = "Country",
                options = listadoPaises,
                modifier = Modifier.fillMaxWidth(),
                onOptionsChosen = { personalDataViewModel.onPaisesChanged(it) })
        }
        item {
            MultiComboBox(
                labelText = "Languages",
                options = listadoPaises,
                modifier = Modifier.fillMaxWidth(),
                onOptionsChosen = { personalDataViewModel.onPaisesChanged(it) })
        }
        item {
            MultiComboBox(
                labelText = "Soft Skills",
                options = listadoPaises,
                modifier = Modifier.fillMaxWidth(),
                onOptionsChosen = { personalDataViewModel.onPaisesChanged(it) })
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

