package com.mvrxopenfm.data.network

import com.mvrxopenfm.data.model.ChannelsGroupResponse
import com.mvrxopenfm.data.model.PlaylistResponse
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @GET("stations/ofm.json")
    fun loadChannels(): Single<ChannelsGroupResponse>

    @GET("playlists/stream_{id}.json?number=10")
    fun loadPlaylist(@Path("id") streamId: Int): Single<List<PlaylistResponse>>
}