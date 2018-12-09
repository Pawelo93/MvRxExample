package com.mvrxopenfm.domain.model

import com.mvrxopenfm.addOrRemove

data class ChannelsGroup(
    val name: String = "",
    val channels: List<Channel> = emptyList(),
    val isExpanded: Boolean = true
) {

    fun addOrRemoveChannel(channel: Channel) = this.copy(channels = channels.addOrRemove(channel))
}