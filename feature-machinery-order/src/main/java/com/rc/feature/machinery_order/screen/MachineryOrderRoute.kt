package com.rc.feature.machinery_order.screen

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rc.feature.machinery_order.navigation.machineryOrderRoute
import com.rc.feature.machinery_order.screen.MachineryOrderEvent.*
import com.rc.machinerybooker.core.design.theme.MachineryBookerTheme
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
    machineryOrderId: Long? = null,
    onScreenChanged: (String) -> Unit,
    onNavigateToMachineryOrderList: () -> Unit,
) {
    val currentUiState = viewModel.uiState.collectAsState().value
    MachineryOrderScreen(
        currentUiState = currentUiState,
        onScreenChanged = onScreenChanged,
        onValueSelect = { value ->
            viewModel.onEvent(
                ValueSelectFromDropDown(
                    value
                )
            )
        },
        onDateTimeSelect = { date, dateType ->
            viewModel.onEvent(
                DateSelect(date, dateType),
            )
        },
        onConfirmOnTime = { checked, dateType ->
            viewModel.onEvent(
                ConfirmOnTime(checked, dateType),
            )
        },
        onSave = {
            viewModel.onEvent(Save)
            onNavigateToMachineryOrderList()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MachineryOrderScreen(
    currentUiState: MachineryOrderState,
    onScreenChanged: (String) -> Unit,
    onValueSelect: (Any) -> Unit,
    onDateTimeSelect: (LocalDateTime, MachineryOrderDateType) -> Unit,
    onConfirmOnTime: (Boolean, MachineryOrderDateType) -> Unit,
    onSave: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        onScreenChanged(machineryOrderRoute)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
                    text = "????????????????????????" // TODO load status
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        RowWithEditableField(
            options = currentUiState.vehicleOptions,
            value = currentUiState.vehicle,
            placeholder = stringResource(id = R.string.select_vehicle),
            onValueChange = {},
            onValueSelect = onValueSelect
        )
        Spacer(modifier = Modifier.height(2.dp))
        RowWithEditableField(
            options = currentUiState.projectOptions,
            value = currentUiState.project,
            placeholder = stringResource(id = R.string.select_project),
            onValueChange = {},
            onValueSelect = onValueSelect
        )
        Spacer(modifier = Modifier.height(2.dp))
        RowWithEditableField(
            options = currentUiState.vehicleOptions,
            value = currentUiState.locationDescription,
            placeholder = stringResource(id = R.string.select_location),
            onValueChange = {},
            onValueSelect = {},
            hasDropDownMenu = false
        )
        Spacer(modifier = Modifier.height(2.dp))
        RowWithDatePickerField(
            value = currentUiState.plannedStartLocalDateTime,
            enabled = true,
            stringValue = currentUiState.plannedStartDateString,
            dateType = MachineryOrderDateType.StartPlanned,
            onValueSelect = onDateTimeSelect
        )
        Spacer(modifier = Modifier.height(6.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            RowWithTimePickerFields(
                sectionCaption = stringResource(id = R.string.start),
                enabled = true,
                plannedLDT = currentUiState.plannedStartLocalDateTime,
                stringPlannedLDT = currentUiState.plannedStartHoursMinutesString,
                actualClientOnTime = currentUiState.actualClientStartOnTime,
                actualClientLDT = currentUiState.actualClientStartLocalDateTime,
                stringActualClientLDT = currentUiState.actualClientStartHoursMinutesString,
                actualProviderOnTime = currentUiState.actualProviderStartOnTime,
                actualProviderLDT = currentUiState.actualProviderStartLocalDateTime,
                stringProviderLDT = currentUiState.actualProviderStartHoursMinutesString,
                machineryOrderDateTypePlanned = MachineryOrderDateType.StartPlanned,
                machineryOrderDateTypeClient = MachineryOrderDateType.StartActualClient,
                machineryOrderDateTypeProvider = MachineryOrderDateType.StartActualProvider,
                onTimeSelect = onDateTimeSelect,
                onConfirmOnTime = onConfirmOnTime
            )
            RowWithTimePickerFields(
                sectionCaption = stringResource(id = R.string.finish),
                enabled = true,
                plannedLDT = currentUiState.plannedFinishLocalDateTime,
                stringPlannedLDT = currentUiState.plannedFinishHoursMinutesString,
                actualClientOnTime = currentUiState.actualClientFinishOnTime,
                actualClientLDT = currentUiState.actualClientFinishLocalDateTime,
                stringActualClientLDT = currentUiState.actualClientFinishHoursMinutesString,
                actualProviderOnTime = currentUiState.actualProviderFinishOnTime,
                actualProviderLDT = currentUiState.actualProviderFinishLocalDateTime,
                stringProviderLDT = currentUiState.actualProviderFinishHoursMinutesString,
                machineryOrderDateTypePlanned = MachineryOrderDateType.FinishPlanned,
                machineryOrderDateTypeClient = MachineryOrderDateType.FinishActualClient,
                machineryOrderDateTypeProvider = MachineryOrderDateType.FinishActualProvider,
                onTimeSelect = onDateTimeSelect,
                onConfirmOnTime = onConfirmOnTime
            )
            RowWithTimePickerFields(
                sectionCaption = stringResource(id = R.string.duration),
                enabled = false,
                plannedLDT = currentUiState.plannedDurationLocalDateTime,
                stringPlannedLDT = currentUiState.plannedDurationHoursMinutesString,
                actualClientOnTime = currentUiState.actualClientFinishOnTime,
                actualClientLDT = currentUiState.actualClientDurationLocalDateTime,
                stringActualClientLDT = currentUiState.actualClientDurationHoursMinutesString,
                actualProviderOnTime = currentUiState.actualProviderFinishOnTime,
                actualProviderLDT = currentUiState.actualProviderDurationLocalDateTime,
                stringProviderLDT = currentUiState.actualProviderDurationHoursMinutesString,
                machineryOrderDateTypePlanned = MachineryOrderDateType.FinishPlanned,
                machineryOrderDateTypeClient = MachineryOrderDateType.FinishActualClient,
                machineryOrderDateTypeProvider = MachineryOrderDateType.FinishActualProvider,
                onTimeSelect = { _, _ -> },
                onConfirmOnTime = { _, _ -> }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = Color.White
                ),
                onClick = onSave
            )
            { Text(text = stringResource(id = R.string.save)) }
        }
    }
}

@Composable
fun RowWithTimePickerFields(
    sectionCaption: String = "",
    enabled: Boolean = true,
    plannedLDT: LocalDateTime,
    stringPlannedLDT: String,
    actualClientOnTime: Boolean,
    actualClientLDT: LocalDateTime,
    stringActualClientLDT: String,
    actualProviderOnTime: Boolean,
    actualProviderLDT: LocalDateTime,
    stringProviderLDT: String,
    machineryOrderDateTypePlanned: MachineryOrderDateType,
    machineryOrderDateTypeClient: MachineryOrderDateType,
    machineryOrderDateTypeProvider: MachineryOrderDateType,
    onTimeSelect: (LocalDateTime, MachineryOrderDateType) -> Unit,
    onConfirmOnTime: (Boolean, MachineryOrderDateType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 12.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
            text = sectionCaption
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TimePickerField(
                value = plannedLDT,
                enabled = enabled,
                label = stringResource(id = R.string.plan),
                stringValue = stringPlannedLDT,
                dateType = machineryOrderDateTypePlanned,
                onValueSelect = onTimeSelect
            )
            Checkbox(
                modifier = Modifier.alpha(if (enabled) 1f else 0f),
                checked = actualClientOnTime,
                enabled = enabled,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primaryContainer
                ),
                onCheckedChange = { checked ->
                    onConfirmOnTime(checked, machineryOrderDateTypeClient)
                })
            TimePickerField(
                value = actualClientLDT,
                enabled = enabled,
                label = stringResource(id = R.string.actual_client),
                stringValue = stringActualClientLDT,
                dateType = machineryOrderDateTypeClient,
                onValueSelect = onTimeSelect
            )
            Checkbox(
                modifier = Modifier.alpha(if (enabled) 1f else 0f),
                checked = actualProviderOnTime,
                enabled = enabled,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primaryContainer
                ),
                onCheckedChange = { checked ->
                    onConfirmOnTime(checked, machineryOrderDateTypeProvider)
                })
            TimePickerField(
                value = actualProviderLDT,
                enabled = enabled,
                label = stringResource(id = R.string.actual_provider),
                stringValue = stringProviderLDT,
                dateType = machineryOrderDateTypeProvider,
                onValueSelect = onTimeSelect
            )
        }
    }
}


@Composable
fun RowWithDatePickerField(
    value: LocalDateTime,
    enabled: Boolean = true,
    stringValue: String,
    dateType: MachineryOrderDateType,
    onValueSelect: (LocalDateTime, MachineryOrderDateType) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(start = 12.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DatePickerField(
            value = value,
            enabled = enabled,
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
    enabled: Boolean = true,
    stringValue: String,
    dateType: MachineryOrderDateType,
    onValueSelect: (LocalDateTime, MachineryOrderDateType) -> Unit
) {
    val dateDialogState = rememberMaterialDialogState()
    OutlinedTextField(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .wrapContentWidth()
            .clickable {
                dateDialogState.show()
            }
            .background(color = MaterialTheme.colorScheme.surfaceTint),
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
    if (enabled) {
        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton(text = stringResource(id = R.string.ok))
                negativeButton(text = stringResource(id = R.string.cancel))
            }
        ) {
            datepicker(
                initialDate = value.toLocalDate(),
                title = stringResource(id = R.string.pick_date)
            ) { selectedDate ->
                onValueSelect(LocalDateTime.of(selectedDate, value.toLocalTime()), dateType)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerField(
    value: LocalDateTime,
    enabled: Boolean = true,
    label: String = "",
    stringValue: String,
    dateType: MachineryOrderDateType,
    onValueSelect: (LocalDateTime, MachineryOrderDateType) -> Unit
) {
    val dateDialogState = rememberMaterialDialogState()
    OutlinedTextField(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .width(76.dp)
            .clickable {
                dateDialogState.show()
            },
        enabled = false,
        label = { Text(label) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = if (enabled) {
                MaterialTheme.colorScheme.surfaceTint
            } else Color.Transparent,
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
    if (enabled) {
        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton(text = stringResource(id = R.string.ok))
                negativeButton(text = stringResource(id = R.string.cancel))
            }
        ) {
            timepicker(
                initialTime = value.toLocalTime(),
                title = stringResource(id = R.string.pick_time),
            ) { selectedTime ->
                onValueSelect(LocalDateTime.of(value.toLocalDate(), selectedTime), dateType)
            }
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
            positiveButton(text = stringResource(id = R.string.ok))
            negativeButton(text = stringResource(id = R.string.cancel))
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
            positiveButton(text = stringResource(id = R.string.ok)) {
            }
            negativeButton(text = stringResource(id = R.string.cancel))
        }
    ) {

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
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
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
    placeholder: String = "",
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
            placeholder = placeholder,
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
    placeholder: String = "",
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
                .background(color = MaterialTheme.colorScheme.surfaceTint),
            value = value.representation().ifEmpty { placeholder },
            readOnly = hasDropDownMenu, // TODO allow text edit even for dropdowns
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
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun RowWithTimePickerFieldsPreview() {
    val mockLocalDateTime = LocalDateTime.of(2022, 12, 10, 17, 25, 10)
    val mockLocalDateTimeString = mockLocalDateTime.format(DateTimeFormatter.ofPattern("hh:mm"))
    MachineryBookerTheme() {
        RowWithTimePickerFields(
            plannedLDT = mockLocalDateTime,
            stringPlannedLDT = mockLocalDateTimeString,
            onTimeSelect = { _, _ -> },
            machineryOrderDateTypePlanned = MachineryOrderDateType.StartPlanned,
            machineryOrderDateTypeClient = MachineryOrderDateType.FinishActualClient,
            machineryOrderDateTypeProvider = MachineryOrderDateType.FinishActualProvider,
            actualClientOnTime = false,
            actualClientLDT = mockLocalDateTime,
            stringActualClientLDT = mockLocalDateTimeString,
            actualProviderOnTime = false,
            actualProviderLDT = mockLocalDateTime,
            stringProviderLDT = mockLocalDateTimeString,
            onConfirmOnTime = { _, _ -> }
        )
    }
}

//@Composable
//@Preview
//fun MachineryOrderScreenPreview() {
//    MachineryOrderScreen(
//        currentUiState = MachineryOrderState(),
//        onValueSelect = {},
//        onDateTimeSelect = { _, _ ->}
//    )
//}
