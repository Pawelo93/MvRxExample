package com.mvrxopenfm.data.model

import com.squareup.moshi.Json

data class Group(
    val name: String,
    val id: Long,
    @Json(name = "stream_ids") val streamIds: List<Long>
)