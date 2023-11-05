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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import proyectofinal.com.example.abc.ui.utils.SharePreference

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun PersonalDataScreen(navController: NavController) {
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
        MainContent(innerPadding, personalDataViewModel, keyboardController, navController)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainContent(
    padding: PaddingValues, personalDataViewModel: PersonalDataViewModel,
    keyboardController: SoftwareKeyboardController?,
    navController: NavController
) {
    val email: String by personalDataViewModel.email.observeAsState(initial = "")
    val name: String by personalDataViewModel.fullName.observeAsState(initial = "")
    val age: String by personalDataViewModel.edad.observeAsState(initial = "")
    val phone: String by personalDataViewModel.phone.observeAsState(initial = "")
    val countries: List<ComboOption> by personalDataViewModel.countries.observeAsState(initial = listOf())
    val languages: List<ComboOption> by personalDataViewModel.languages.observeAsState(initial = listOf())
    val softSkills: List<ComboOption> by personalDataViewModel.softSkills.observeAsState(initial = listOf())
    val skills: List<ComboOption> by personalDataViewModel.skills.observeAsState(initial = listOf())
    val countrySelected: List<ComboOption> by personalDataViewModel.countrySelected.observeAsState(initial = listOf())
    val languagesSelected: List<ComboOption> by personalDataViewModel.languagesSelected.observeAsState(
        initial = listOf()
    )
    val softSkillsSelected: List<ComboOption> by personalDataViewModel.softSkillsSelected.observeAsState(
        initial = listOf()
    )
    val skillsSelected: List<ComboOption> by personalDataViewModel.skillsSelected.observeAsState(
        initial = listOf()
    )
    val sharePreference = SharePreference(LocalContext.current)
    personalDataViewModel.getInfoInicial(sharePreference)
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
                text = stringResource(id = R.string.personal_data),
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
                label = stringResource(id = R.string.name),
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { personalDataViewModel.onFullNameChanged(it) })
        }
        item {
            TextFieldABC(
                textField = age,
                label = stringResource(id = R.string.age),
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { personalDataViewModel.onAgeChanged(it) })
        }
        item {
            TextFieldABC(
                textField = email,
                label = stringResource(id = R.string.email),
                isEditable = false,
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { })

        }
        item {
            TextFieldABC(
                textField = phone,
                label = stringResource(id = R.string.phone),
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxWidth(),
                onTextFieldChanged = { personalDataViewModel.onPhoneChanged(it) })
        }

        item {
            SingleComboBox(
                labelText = stringResource(id = R.string.country),
                options = countries,
                modifier = Modifier.fillMaxWidth(),
                onOptionsChosen = { personalDataViewModel.onPaisesChanged(it) },
                selectedIds = countrySelected.map { it.id })
        }
        item {
            MultiComboBox(
                labelText = stringResource(id = R.string.languages),
                options = languages,
                modifier = Modifier.fillMaxWidth(),
                onOptionsChosen = { personalDataViewModel.onLanguagesChanged(it) },
                selectedIds = languagesSelected.map { it.id }
            )
        }
        item {
            MultiComboBox(
                labelText = stringResource(id = R.string.soft_skills),
                options = softSkills,
                modifier = Modifier.fillMaxWidth(),
                onOptionsChosen = { personalDataViewModel.onSoftSkillsChanged(it) },
                selectedIds = softSkillsSelected.map { it.id })
        }
        item {
            MultiComboBox(
                labelText = stringResource(id = R.string.skills),
                options = skills,
                modifier = Modifier.fillMaxWidth(),
                onOptionsChosen = { personalDataViewModel.onSkillsChanged(it) },
                selectedIds = skillsSelected.map { it.id })
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
                    onClick = { personalDataViewModel.onSaveInfoClicked(onSaveSuccess = {
                        navController.navigate("ProfileScreen") {
                            popUpTo("ProfileScreen") {
                                inclusive = true

                            }
                        }
                    }) },

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
