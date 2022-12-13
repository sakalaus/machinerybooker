package com.rc.feature.machinery_order.screen

import androidx.lifecycle.ViewModel
import com.rc.machinerybooker.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MachineryOrderViewModel(
    val userCases: UseCases
): ViewModel() {

    fun init(){

    }



}