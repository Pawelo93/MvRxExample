package com.mvrxopenfm.ui.channels

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.mvrxopenfm.domain.model.ChannelsGroup

data class ChannelGroupState(
    val channelsGroups: List<ChannelsGroup> = emptyList(),
    val hiddenGroups: List<String> = emptyList(),
    val request: Async<List<ChannelsGroup>> = Uninitialized
) : MvRxState {

    fun isGroupHidden(groupName: String) = hiddenGroups.contains(groupName)
}