package com.rc.machinerybooker.feature.machineryorderlist.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rc.machinerybooker.domain.entities.*
import com.rc.machinerybooker.domain.usecases.UseCases
import com.rc.machinerybooker.feature.machineryorderlist.screen.MachineryOrderEvent.MachineryOrderClicked
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MachineryOrderListViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _uiState = MutableStateFlow(MachineryOrderListState())
    val uiState = _uiState.asStateFlow()

    private val machineryOrderFilter = MachineryOrderFilter()

    init {
        initializeObservers()
    }

    private fun initializeObservers() {
        useCases.observeMachineryOrderExtendedDataList(machineryOrderFilter)
            .onStart {
                _uiState.update { it.copy(isLoading = false) }
            }
            .onEach { machineryOrdersMap ->
                _uiState.update { it.copy(machineryOrdersMap = machineryOrdersMap) }
            }.launchIn(viewModelScope)
    }

    fun onEvent(event: MachineryOrderEvent){
        when (event) {
            is MachineryOrderClicked -> onMachineryOrderClick(machineryOrderId = event.machineryOrderId)
        }
    }

    private fun onMachineryOrderClick(machineryOrderId: Long){

    }

}

