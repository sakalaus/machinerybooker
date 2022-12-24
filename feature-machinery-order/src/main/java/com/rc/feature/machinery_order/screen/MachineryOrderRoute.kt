package com.rc.feature.machinery_order.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rc.machinerybooker.feature.machinery_order.R
import com.rc.machinerybooker.domain.entities.representation
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun MachineryOrderRoute(
    viewModel: MachineryOrderViewModel = hiltViewModel(),
    machineryOrderId: Long? = null
) {
    val currentUiState = viewModel.uiState.collectAsState().value
    MachineryOrderScreen(
        currentUiState = currentUiState,
        onValueSelect = { value ->
            viewModel.onEvent(
                MachineryOrderEvent.ValueSelectFromDropDown(
                    value
                )
            )
        },
        onDateSelect = { date, dateType -> viewModel.onEvent(
            MachineryOrderEvent.DateSelect(date, dateType),
        )}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MachineryOrderScreen(
    currentUiState: MachineryOrderState,
    onValueSelect: (Any) -> Unit,
    onDateSelect: (LocalDate, MachineryOrderDateType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedCardSelectable(value = currentUiState.clientDepartment)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.headlineMedium,
                text = "->"
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedCardSelectable(value = currentUiState.providerDepartment)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedCard(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 4.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primaryContainer),
            ) {
                Text(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                        .padding(4.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    text = "Подтверждена"
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        RowWithEditableField(
            options = currentUiState.vehicleOptions,
            value = currentUiState.vehicle,
            onValueChange = {},
            onValueSelect = onValueSelect
        )
        Spacer(modifier = Modifier.height(2.dp))
        RowWithEditableField(
            options = currentUiState.projectOptions,
            value = currentUiState.project,
            onValueChange = {},
            onValueSelect = onValueSelect
        )
        Spacer(modifier = Modifier.height(2.dp))
        RowWithEditableField(
            options = currentUiState.vehicleOptions,
            value = currentUiState.locationDescription,
            onValueChange = {},
            onValueSelect = {},
            hasDropDownMenu = false
        )
        Spacer(modifier = Modifier.height(2.dp))
        RowWithDatePickerField(
            value = currentUiState.plannedStartLocalDateTime,
            stringValue = currentUiState.plannedStartDateString,
            dateType = MachineryOrderDateType.StartPlanned,
            onValueSelect = onDateSelect
        )
    }
}

@Composable
fun ColumnScope.RowWithDatePickerField(
    value: LocalDateTime,
    stringValue: String,
    dateType: MachineryOrderDateType,
    onValueSelect: (LocalDate, MachineryOrderDateType) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DatePickerField(
            value = value,
            stringValue = stringValue,
            dateType = dateType,
            onValueSelect = onValueSelect
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerField(
    value: LocalDateTime,
    stringValue: String,
    dateType: MachineryOrderDateType,
    onValueSelect: (LocalDate, MachineryOrderDateType) -> Unit
) {
    val dateDialogState = rememberMaterialDialogState()
    OutlinedTextField(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .wrapContentWidth()
            .clickable {
                dateDialogState.show()
            }
            .background(color = Color(0xFFDCE1E8)),
        enabled = false,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
            disabledBorderColor = MaterialTheme.colorScheme.primaryContainer,
            disabledTextColor = MaterialTheme.colorScheme.scrim
        ),
        value = stringValue,
        onValueChange = {},

        readOnly = true,
        maxLines = 1
    )
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "Ok") {
            }
            negativeButton(text = "Cancel")
        }
    ) {
        datepicker(
            initialDate = value.toLocalDate(),
            title = "Pick a date"
        ) {
            onValueSelect(it, dateType)
        }
    }
}


@Composable
fun DateTimePickerField() {
    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var pickedTime by remember {
        mutableStateOf(LocalTime.NOON)
    }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(pickedDate)
        }
    }
    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("hh:mm")
                .format(pickedTime)
        }
    }

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            dateDialogState.show()
        }) {
            Text(text = "Pick date")
        }
        Text(text = formattedDate)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            timeDialogState.show()
        }) {
            Text(text = "Pick time")
        }
        Text(text = formattedTime)
    }
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "Ok") {
            }
            negativeButton(text = "Cancel")
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
            allowedDateValidator = {
                it.dayOfMonth % 2 == 1
            }
        ) {
            pickedDate = it
        }
    }
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "Ok") {
            }
            negativeButton(text = "Cancel")
        }
    ) {
        timepicker(
            initialTime = LocalTime.NOON,
            title = "Pick a time",
            timeRange = LocalTime.MIDNIGHT..LocalTime.NOON
        ) {
            pickedTime = it
        }
    }
}


@Composable
fun RowScope.OutlinedCardSelectable(
    value: Any?,
) {
    OutlinedCard(
        modifier = Modifier
            .requiredWidth(96.dp)
            .padding(horizontal = 4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primaryContainer)
    ) {
        Text(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.headlineSmall,
            text = value.representation()
        )
    }
}

@Composable
fun ColumnScope.RowWithEditableField(
    options: List<Any> = emptyList<Any>(),
    value: Any? = null,
    onValueChange: () -> Unit,
    onValueSelect: (Any) -> Unit,
    hasDropDownMenu: Boolean = true
) {
    Row(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SelectableField(
            options = options,
            value = value,
            onValueChange = onValueChange,
            onValueSelect = onValueSelect,
            hasDropDownMenu = hasDropDownMenu
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectableField(
    options: List<Any> = emptyList(),
    value: Any? = null,
    onValueChange: () -> Unit,
    onValueSelect: (Any) -> Unit,
    hasDropDownMenu: Boolean
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .menuAnchor()
                .fillMaxWidth()
                .background(color = Color(0xFFDCE1E8)),
            value = value.representation(),
            readOnly = hasDropDownMenu, // TODO allow text edit even for dropdowns
            placeholder = { stringResource(id = R.string.select_vehicle) },
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
            ),
            onValueChange = {
                onValueChange()
            },
            trailingIcon = if (hasDropDownMenu) {
                {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primaryContainer
                        )
                    }
                }
            } else null
        )
        if (hasDropDownMenu) {
            ExposedDropdownMenu(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { currentOption ->
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            onValueSelect(currentOption)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        text = { Text(text = currentOption.representation()) })
                }
            }
        }
    }
}

@Composable
@Preview
fun MachineryOrderScreenPreview() {
    MachineryOrderScreen(
        currentUiState = MachineryOrderState(),
        onValueSelect = {},
        onDateSelect = {_,_ ->}
    )
}
