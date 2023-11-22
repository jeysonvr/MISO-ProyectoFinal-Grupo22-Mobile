package proyectofinal.com.example.abc.ui.interview

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
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import proyectofinal.com.example.abc.R
import proyectofinal.com.example.abc.model.InterviewOutDTOItem
import proyectofinal.com.example.abc.ui.utils.SharePreference


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun InterviewScreen(navController: NavController, viewModel: InterviewViewModel = hiltViewModel()) {
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
        }
    ) { innerPadding ->
        MainContent(innerPadding, viewModel, keyboardController)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun MainContent(
    padding: PaddingValues, interviewViewModel: InterviewViewModel,
    keyboardController: SoftwareKeyboardController?
) {
    val interviewList: List<InterviewOutDTOItem>? by interviewViewModel.listInterviews!!.observeAsState(
        initial = listOf()
    )
    val showDialog: Boolean by interviewViewModel.showDialog.observeAsState(initial = false)
    val isDetail: Boolean by interviewViewModel.isDetail.observeAsState(initial = false)
    val comment: String by interviewViewModel.comment.observeAsState(initial = "")
    val sharePreference = SharePreference(LocalContext.current)
    interviewViewModel.getInitialData(sharePreference)
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
                text = stringResource(id = R.string.scheduled_interviews),
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
        if (interviewList?.isNotEmpty() == true) {
            interviewList!!.forEach {
                item {
                    itemInterviewData(
                        companyName = it.asunto,
                        startDate = "${it.fecha} ${it.hora_fin}",
                        isDetail,
                        details = it.resultados,
                        interviewViewModel = interviewViewModel
                    )
                }
            }
        } else {
            item {
                Text(
                    text = stringResource(id = R.string.no_scheduled_interviews),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )
            }
        }
    }
    if (showDialog) {
        CustomDialog(
            onDismiss = { interviewViewModel.hideDialog() },
            // Agrega aquí los elementos o contenido del diálogo
            content = {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.comment),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = comment, color = Color.Black)
                    // Otros elementos del diálogo
                }
            }
        )
    }
}

@Composable
fun CustomDialog(
    onDismiss: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 16.dp,
            tonalElevation = 16.dp
        ) {
            content(PaddingValues(16.dp))
        }
    }
}

@Composable
private fun itemInterviewData(
    companyName: String?,
    startDate: String?,
    isDetalle: Boolean,
    details: String?,
    interviewViewModel: InterviewViewModel
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column {
            Row(modifier = Modifier) {
                Image(
                    painter = painterResource(id = R.drawable.img_interview),
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
                    val (nameCompany, time, detail) = createRefs()
                    Text(text = companyName!!, modifier = Modifier
                        .constrainAs(nameCompany) {
                            top.linkTo(parent.top, margin = 2.dp)
                            start.linkTo(parent.start, margin = 20.dp)
                        }
                        .fillMaxWidth(1f), fontWeight = FontWeight.Bold)
                    Row(modifier = Modifier
                        .constrainAs(time) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start, margin = 20.dp)
                        }
                        .fillMaxWidth(1f)) {
                        Text(text = startDate!!, modifier = Modifier.fillMaxWidth())
                    }
                    if (isDetalle && details != null) {
                        Row(modifier = Modifier
                            .constrainAs(detail) {
                                bottom.linkTo(parent.bottom, margin = 2.dp)
                                start.linkTo(parent.start, margin = 20.dp)
                            }
                            .fillMaxWidth(1f)) {
                            Text(
                                text = stringResource(id = R.string.check_results),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { interviewViewModel.showDialog(details) },
                                color = colorResource(id = R.color.borderText),
                                style = TextStyle(textDecoration = TextDecoration.Underline)
                            )

                        }
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