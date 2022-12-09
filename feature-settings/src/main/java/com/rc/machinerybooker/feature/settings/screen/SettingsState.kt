package com.rc.machinerybooker.feature.settings.screen

import com.rc.machinerybooker.domain.entities.MachineryOrder

data class SettingsState(
    val machineryOrders: List<MachineryOrder>
)
