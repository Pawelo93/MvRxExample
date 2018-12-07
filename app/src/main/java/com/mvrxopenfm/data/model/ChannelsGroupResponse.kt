package com.mvrxopenfm.data.model

import com.squareup.moshi.Json

data class ChannelsGroupResponse(
    @Json(name = "channels") val wsChannels: List<Channel>,
    @Json(name = "groups") val wsGroups: List<Group>
)