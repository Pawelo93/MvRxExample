package com.mvrxopenfm.data.model

import com.squareup.moshi.Json

data class ChannelsGroupResponse(
    @Json(name = "channels") val wsChannelResponses: List<ChannelResponse>,
    @Json(name = "groups") val wsGroupResponses: List<GroupResponse>
)

data class ChannelResponse(
    @Json(name = "channel_id") val channelId: Long?,
    @Json(name = "stream_id") val streamId: Long?,
    @Json(name = "mnt") val mountPoint: String?,
    val name: String?
)

data class GroupResponse(
    val name: String,
    val id: Long,
    @Json(name = "stream_ids") val streamIds: List<Long>
)