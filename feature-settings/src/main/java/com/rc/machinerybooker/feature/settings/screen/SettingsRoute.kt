package com.rc.machinerybooker.feature.settings.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.rc.machinerybooker.domain.entities.MachineryOrder

@Composable
fun SettingsRoute(
    viewModel: SettingsViewModel  = hiltViewModel()
){
    SettingsScreen()
}

@Composable
fun SettingsScreen(){

}