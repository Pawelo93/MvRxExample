package com.mvrxopenfm.data.network

import com.mvrxopenfm.data.model.ChannelsGroupResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("stations/ofm.json")
    fun loadChannels(): Single<ChannelsGroupResponse>
}