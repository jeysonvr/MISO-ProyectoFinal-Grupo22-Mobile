package proyectofinal.com.example.abc.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


data class ComboOption(
    override val descripcion: String,
    override val id: Int,
) : SelectableOption

interface SelectableOption {
    val id: Int
    val descripcion: String
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiComboBox(
    labelText: String,
    options: List<ComboOption>,
    onOptionsChosen: (List<ComboOption>) -> Unit,
    modifier: Modifier = Modifier,
    selectedIds: List<Int> = emptyList(),
) {
    var expanded by remember { mutableStateOf(false) }
    // when no options available, I want ComboBox to be disabled
    val isEnabled by rememberUpdatedState { options.isNotEmpty() }
    var selectedOptionsList  = remember { mutableStateListOf<Int>() }
    selectedOptionsList.clear()
    //Initial setup of selected ids
    if (selectedIds.isNotEmpty()){


        selectedIds.forEach{
            selectedOptionsList.add(it)
        }
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            if (isEnabled()) {
                expanded = !expanded
                if (!expanded) {
                    onOptionsChosen(options.filter { it.id in selectedOptionsList }.toList())
                }
            }
        },
        modifier = modifier,
    ) {
        val selectedSummary = when (selectedOptionsList.size) {
            0 -> ""
            1 -> options.first { it.id == selectedOptionsList.first() }.descripcion
            else -> getSelectedOptions(selectedOptionsList, options)
        }
        TextField(
            enabled = isEnabled(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            value = selectedSummary,
            onValueChange = {},
            label = { Text(text = labelText) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                textColor = Color(0XFF0DA89B),
                cursorColor = Color(0XFF0DA89B),
                focusedIndicatorColor = Color(0XFF0DA89B),
                unfocusedIndicatorColor = Color(0XFF0DA89B),
                disabledIndicatorColor = Color(0XFF0DA89B),
            ),
        )
        ExposedDropdownMenu(
            modifier = Modifier.background(Color.White),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                onOptionsChosen(options.filter { it.id in selectedOptionsList }.toList())
            },
        ) {
            for (option in options) {

                //use derivedStateOf to evaluate if it is checked
                var checked = remember {
                    derivedStateOf{option.id in selectedOptionsList}
                }.value

                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(Color.White)
                                .fillMaxSize()) {
                            Checkbox(
                                checked = checked,
                                onCheckedChange = { newCheckedState ->
                                    if (newCheckedState) {
                                        selectedOptionsList.add(option.id)
                                    } else {
                                        selectedOptionsList.remove(option.id)
                                    }
                                },
                            )
                            Text(text = option.descripcion)
                        }
                    },
                    onClick = {
                        if (!checked) {
                            selectedOptionsList.add(option.id)
                        } else {
                            selectedOptionsList.remove(option.id)
                        }
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleComboBox(
    labelText: String,
    options: List<ComboOption>,
    onOptionsChosen: (List<ComboOption>) -> Unit,
    modifier: Modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
    selectedIds: List<Int> = emptyList(),
) {
    var expanded by remember { mutableStateOf(false) }
    // when no options available, I want ComboBox to be disabled
    val isEnabled by rememberUpdatedState { options.isNotEmpty() }
    var selectedOptionsList  = remember { mutableStateListOf<Int>() }
    selectedOptionsList.clear()
    //Initial setup of selected ids
    selectedIds.forEach{

        selectedOptionsList.add(it)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            if (isEnabled()) {
                expanded = !expanded
                if (!expanded) {
                    onOptionsChosen(options.filter { it.id in selectedOptionsList }.toList())
                }
            }
        },
        modifier = modifier,
    ) {
        val selectedSummary = when (selectedOptionsList.size) {
            0 -> ""
            1 -> options.first { it.id == selectedOptionsList.first() }.descripcion
            else -> getSelectedOptions(selectedOptionsList, options)
        }
        TextField(
            enabled = isEnabled(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                textColor = Color(0XFF0DA89B),
                cursorColor = Color(0XFF0DA89B),
                focusedIndicatorColor = Color(0XFF0DA89B),
                unfocusedIndicatorColor = Color(0XFF0DA89B),
                disabledIndicatorColor = Color(0XFF0DA89B),
            ),
            readOnly = true,
            value = selectedSummary,
            onValueChange = {},
            label = { Text(text = labelText) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )
        ExposedDropdownMenu(
            modifier = Modifier.background(Color.White),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                onOptionsChosen(options.filter { it.id in selectedOptionsList }.toList())
            },
        ) {
            for (option in options) {

                //use derivedStateOf to evaluate if it is checked
                var checked = remember {
                    derivedStateOf{option.id in selectedOptionsList}
                }.value

                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = checked,
                                onCheckedChange = { newCheckedState ->
                                    if(selectedOptionsList.size == 0){
                                        if (newCheckedState) {
                                            selectedOptionsList.add(option.id)
                                        } else {
                                            selectedOptionsList.remove(option.id)
                                        }
                                    } else
                                    {
                                        if (newCheckedState) {
                                            selectedOptionsList.remove(selectedOptionsList.first())
                                            selectedOptionsList.add(option.id)
                                        }
                                        else {
                                            selectedOptionsList.remove(option.id)
                                        }
                                    }
                                },
                            )
                            Text(text = option.descripcion)
                        }
                    },
                    onClick = {

                        if (!checked) {
                            if (selectedOptionsList.size == 0){
                                selectedOptionsList.add(option.id)
                            } else {
                                selectedOptionsList.remove(selectedOptionsList.first())
                                selectedOptionsList.add(option.id)
                            }
                        } else {
                            selectedOptionsList.remove(option.id)
                        }

                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

fun getSelectedOptions(selectedOptionsList: List<Int>, options: List<ComboOption>): String {
    var selectedOptions = ""
    for (selectedOption in selectedOptionsList) {
        if (selectedOptions.isNotEmpty()) {
            selectedOptions += ", "
        }
        selectedOptions += options.first { it.id == selectedOption }.descripcion
    }
    return selectedOptions
}