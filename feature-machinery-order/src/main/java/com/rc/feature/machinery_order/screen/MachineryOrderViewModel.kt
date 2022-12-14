package com.rc.feature.machinery_order.screen

import androidx.lifecycle.ViewModel
import com.rc.machinerybooker.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MachineryOrderViewModel @Inject constructor(
    val userCases: UseCases
): ViewModel() {

    fun init(){

    }

    fun onEvent(
        event: MachineryOrderEvent
    ){

    }



}