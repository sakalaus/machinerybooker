package com.rc.feature.machinery_order.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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

@Composable
fun MachineryOrderRoute(
    viewModel: MachineryOrderViewModel = hiltViewModel()
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
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MachineryOrderScreen(
    currentUiState: MachineryOrderState,
    onValueSelect: (Any) -> Unit
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
    }
}

@Composable
fun RowScope.OutlinedCardSelectable(
    value: Any?,
) {
    OutlinedCard(
        modifier = Modifier
            .wrapContentSize()
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
            readOnly = false,
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
        onValueSelect = {}
    )
}
