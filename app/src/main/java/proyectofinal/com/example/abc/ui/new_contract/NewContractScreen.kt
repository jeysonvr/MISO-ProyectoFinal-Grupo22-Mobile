package proyectofinal.com.example.abc.ui.new_contract

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
import proyectofinal.com.example.abc.ui.utils.ComboOption
import proyectofinal.com.example.abc.ui.utils.SharePreference
import proyectofinal.com.example.abc.ui.utils.SingleComboBox
import proyectofinal.com.example.abc.ui.utils.mToast

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NewContractScreen(
    navController: NavController,
    viewModel: NewContractViewModel = hiltViewModel()
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
    ) { innerPadding ->
        MainContent(innerPadding, viewModel, keyboardController, navController)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun MainContent(
    padding: PaddingValues, newContractViewModel: NewContractViewModel,
    keyboardController: SoftwareKeyboardController?,
    navController: NavController
) {
    val applicant: List<ComboOption> by newContractViewModel.applicant.observeAsState(initial = listOf())
    val company: List<ComboOption> by newContractViewModel.company.observeAsState(initial = listOf())
    val project: List<ComboOption> by newContractViewModel.project.observeAsState(initial = listOf())
    val rol: List<ComboOption> by newContractViewModel.rol.observeAsState(initial = listOf())
    val applicantSelected: List<ComboOption>? by newContractViewModel.applicantSelected.observeAsState(
        initial = listOf()
    )
    val companySelected: List<ComboOption>? by newContractViewModel.companySelected!!.observeAsState(
        initial = listOf()
    )
    val projectSelected: List<ComboOption>? by newContractViewModel.projectSelected!!.observeAsState(
        initial = listOf()
    )
    val rolSelected: List<ComboOption>? by newContractViewModel.rolSelected!!.observeAsState(
        initial = listOf()
    )
    val mContext = LocalContext.current
    val sharePreference = SharePreference(mContext)
    newContractViewModel.getInfoInicial(sharePreference)
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
                text = stringResource(id = R.string.new_contract),
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
            SingleComboBox(
                labelText = stringResource(id = R.string.applicant),
                options = applicant,
                modifier = Modifier.fillMaxWidth(),
                onOptionsChosen = { newContractViewModel.onApplicantChanged(it) },
                selectedIds = applicantSelected?.map { it.id })
        }
        item {
            SingleComboBox(
                labelText = stringResource(id = R.string.company),
                options = company,
                modifier = Modifier.fillMaxWidth(),
                onOptionsChosen = { newContractViewModel.onCompanyChanged(it) },
                selectedIds = companySelected?.map { it.id }
            )
        }
        item {
            SingleComboBox(
                labelText = stringResource(id = R.string.project),
                options = project,
                modifier = Modifier.fillMaxWidth(),
                onOptionsChosen = { newContractViewModel.onProjectChanged(it) },
                selectedIds = projectSelected?.map { it.id })
        }
        item {
            SingleComboBox(
                labelText = stringResource(id = R.string.rol),
                options = rol,
                modifier = Modifier.fillMaxWidth(),
                onOptionsChosen = { newContractViewModel.onRolChanged(it) },
                selectedIds = rolSelected?.map { it.id })
        }
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
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
                    onClick = {
                        newContractViewModel.onSaveInfoClicked(
                            onSaveSuccess = {
                                navController.popBackStack()
                                mToast(
                                    context = mContext,
                                    message = mContext.getString(R.string.save_contract)
                                )
                            },
                            onSaveFailed = {
                                mToast(
                                    context = mContext,
                                    message = mContext.getString(R.string.error_contract)
                                )
                            })
                    },

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