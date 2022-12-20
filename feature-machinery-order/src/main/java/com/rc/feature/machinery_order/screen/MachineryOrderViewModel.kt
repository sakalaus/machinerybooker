package com.rc.feature.machinery_order.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rc.machinerybooker.domain.entities.Department
import com.rc.machinerybooker.domain.entities.Project
import com.rc.machinerybooker.domain.entities.Vehicle
import com.rc.machinerybooker.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MachineryOrderViewModel @Inject constructor(
    private val userCases: UseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(MachineryOrderState())
    val uiState = _uiState.asStateFlow()
    val currentUiState = uiState.value

    init {
        initializeObservers()
    }

    private fun initializeObservers() {

        userCases.observeVehicles()
            .onEach { vehicles ->
                _uiState.update { it.copy(vehicleOptions = vehicles) }
            }
            .launchIn(viewModelScope)

        userCases.observeProjects()
            .onEach { projects ->
                _uiState.update { it.copy(projectOptions = projects) }
            }
            .launchIn(viewModelScope)

        userCases.observeDepartments()
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