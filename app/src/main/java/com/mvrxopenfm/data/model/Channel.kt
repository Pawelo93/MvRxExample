package com.mvrxopenfm.data.model

import com.squareup.moshi.Json

data class Channel(
    @Json(name = "channel_id") val channelId: Long? = null,
    @Json(name = "stream_id") val streamId: Long? = null,
    @Json(name = "mnt") val mountPoint: String? = null,
    val name: String? = null
)