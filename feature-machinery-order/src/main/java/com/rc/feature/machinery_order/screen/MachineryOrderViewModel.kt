package com.rc.feature.machinery_order.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rc.feature.machinery_order.screen.MachineryOrderDateType.*
import com.rc.machinerybooker.core.utils.toLong
import com.rc.machinerybooker.domain.entities.MachineryOrderFilter
import com.rc.machinerybooker.domain.entities.Project
import com.rc.machinerybooker.domain.entities.Vehicle
import com.rc.machinerybooker.domain.mock.mockDepartments
import com.rc.machinerybooker.domain.usecases.UseCases
import com.rc.machinerybooker.domain.usecases.extendedMachineryOrderMapType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.time.LocalDateTime
import java.util.Calendar
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
            if (machineryOrderId != -1L) {
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
                                actualClientStartTimeStamp = machineryOrder.actualClientStartTimeStamp,
                                actualProviderStartTimeStamp = machineryOrder.actualProviderStartTimeStamp,
                                plannedFinishTimeStamp = machineryOrder.plannedFinishTimeStamp,
                                actualClientFinishTimeStamp = machineryOrder.actualClientFinishTimeStamp,
                                actualProviderFinishTimeStamp = machineryOrder.actualProviderFinishTimeStamp,
                                createdTimeStamp = machineryOrder.createdTimeStamp
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }
            else {
                val now = Calendar.getInstance().timeInMillis
                _uiState.update {
                    it.copy(
                        clientDepartment = mockDepartments[0],
                        providerDepartment = mockDepartments[1],
                        plannedStartTimeStamp = now,
                        actualClientStartTimeStamp = now,
                        actualProviderStartTimeStamp = now,
                        plannedFinishTimeStamp = now + 3600000,
                        actualClientFinishTimeStamp = now + 3600000,
                        actualProviderFinishTimeStamp = now + 3600000
                    )
                }
            }
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
            is MachineryOrderEvent.ConfirmOnTime -> onConfirmOnTime(event.checked, event.dateType)
            else -> Unit
        }
    }

    private fun onConfirmOnTime(checked: Boolean, dateType: MachineryOrderDateType) {
        when (dateType) {
            StartActualClient -> _uiState.update {
                it.copy(
                    actualClientStartOnTime = checked
                )
            }
            StartActualProvider -> _uiState.update {
                it.copy(
                    actualProviderStartOnTime = checked
                )
            }
            FinishActualClient -> _uiState.update {
                it.copy(
                    actualClientFinishOnTime = checked
                )
            }
            FinishActualProvider -> _uiState.update {
                it.copy(
                    actualProviderFinishOnTime = checked
                )
            }
            else -> Unit
        }
        if (checked) {
            when (dateType) {
                StartActualClient -> _uiState.update {
                    it.copy(
                        actualClientStartTimeStamp = it.plannedStartTimeStamp
                    )
                }
                StartActualProvider -> _uiState.update {
                    it.copy(
                        actualProviderStartTimeStamp = it.plannedStartTimeStamp
                    )
                }
                FinishActualClient -> _uiState.update {
                    it.copy(
                        actualClientFinishTimeStamp = it.plannedFinishTimeStamp
                    )
                }
                FinishActualProvider -> _uiState.update {
                    it.copy(
                        actualProviderFinishTimeStamp = it.plannedFinishTimeStamp
                    )
                }
                else -> Unit
            }
        }
    }

    private fun onSelectDateTime(value: LocalDateTime, dateType: MachineryOrderDateType) {
        when (dateType) {
            StartPlanned -> _uiState.update {
                it.copy(
                    plannedStartTimeStamp = value.toLong()
                )
            }
            StartActualClient -> _uiState.update {
                it.copy(
                    actualClientStartTimeStamp = value.toLong()
                )
            }
            StartActualProvider -> _uiState.update {
                it.copy(
                    actualProviderStartTimeStamp = value.toLong()
                )
            }
            FinishPlanned -> _uiState.update {
                it.copy(
                    plannedFinishTimeStamp = value.toLong()
                )
            }
            FinishActualClient -> _uiState.update {
                it.copy(
                    actualClientFinishTimeStamp = value.toLong()
                )
            }
            FinishActualProvider -> _uiState.update {
                it.copy(
                    actualProviderFinishTimeStamp = value.toLong()
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