package com.mvrxopenfm.ui.details

import android.os.Parcelable
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.mvrxopenfm.domain.model.Channel
import com.mvrxopenfm.domain.model.Playlist
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailsStateArgs(val channelName: String, val streamId: Long) : Parcelable

data class DetailsState(
    val channel: Channel = Channel(),
    val playlist: Playlist = Playlist(),
    val request: Async<Playlist> = Uninitialized
) : MvRxState {

    val streamId: Long
        get() = channel.streamId

    constructor(args: DetailsStateArgs) : this(channel = Channel(args.channelName, args.streamId))
}