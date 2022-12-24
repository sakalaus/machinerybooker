package com.rc.feature.machinery_order.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rc.feature.machinery_order.screen.MachineryOrderDateType.StartPlanned
import com.rc.machinerybooker.core.utils.toLong
import com.rc.machinerybooker.domain.entities.MachineryOrderFilter
import com.rc.machinerybooker.domain.entities.Project
import com.rc.machinerybooker.domain.entities.Vehicle
import com.rc.machinerybooker.domain.usecases.UseCases
import com.rc.machinerybooker.domain.usecases.extendedMachineryOrderMapType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MachineryOrderViewModel @Inject constructor(
    private val useCases: UseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(MachineryOrderState())
    val uiState = _uiState.asStateFlow()
    val currentUiState = uiState.value

    init {
        initializeObservers()
    }

    private fun initializeObservers() {

        savedStateHandle.get<Long>("machineryOrderId")?.let { machineryOrderId ->
            useCases.observeMachineryOrderExtendedDataList(
                machineryOrderFilter = MachineryOrderFilter(id = machineryOrderId)
            ).onEach { machineryOrderMap: extendedMachineryOrderMapType ->
                machineryOrderMap.entries.first().let { machineryOrderData ->
                    val machineryOrder = machineryOrderData.key
                    val extendedData = machineryOrderData.value
                    _uiState.update {
                        it.copy(
                            id = machineryOrder.id,
                            externalId = machineryOrder.externalId,
                            description = machineryOrder.description,
                            locationDescription = machineryOrder.location,
                            clientDepartment = extendedData.clientDepartment,
                            providerDepartment = extendedData.providerDepartment,
                            project = extendedData.project,
                            vehicle = extendedData.vehicle,
                            plannedStartTimeStamp = machineryOrder.plannedStartTimeStamp,
                            createdTimeStamp = machineryOrder.createdTimeStamp
                        )
                    }
                }
            }
                .launchIn(viewModelScope)
        }

        useCases.observeVehicles()
            .onEach { vehicles ->
                _uiState.update { it.copy(vehicleOptions = vehicles) }
            }
            .launchIn(viewModelScope)

        useCases.observeProjects()
            .onEach { projects ->
                _uiState.update { it.copy(projectOptions = projects) }
            }
            .launchIn(viewModelScope)

        useCases.observeDepartments()
            .onEach { departments ->
                _uiState.update { it.copy(departmentOptions = departments) }
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(
        event: MachineryOrderEvent
    ) {
        when (event) {
            is MachineryOrderEvent.ValueSelectFromDropDown -> onSelectFromDropDown(event.value)
            is MachineryOrderEvent.DateSelect -> onSelectDateTime(event.value, event.dateType)
            else -> Unit
        }
    }

    private fun onSelectDateTime(value: LocalDateTime, dateType: MachineryOrderDateType) {
        when (dateType) {
            StartPlanned -> _uiState.update {
                it.copy(
                    plannedStartTimeStamp = value.toLong()
                )
            }
            else -> Unit
        }
    }

    private fun onSelectFromDropDown(value: Any) {
        when (value) {
            is Vehicle -> onSelectVehicle(value)
            is Project -> onSelectProject(value)
            else -> Unit
        }
    }

    private fun onSelectVehicle(vehicle: Vehicle) {
        _uiState.update { it.copy(vehicle = vehicle) }
    }

    private fun onSelectProject(project: Project) {
        _uiState.update { it.copy(project = project) }
    }


}