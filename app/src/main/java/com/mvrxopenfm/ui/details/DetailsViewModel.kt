package com.mvrxopenfm.ui.details

import android.support.v4.app.FragmentActivity
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.args
import com.mvrxopenfm.base.MvRxViewModel
import com.mvrxopenfm.domain.model.Playlist
import com.mvrxopenfm.domain.useCase.LoadPlaylistForChannelUseCase
import org.koin.android.ext.android.inject

class DetailsViewModel(
    initialStateChannel: DetailsState,
    private val loadPlaylistForChannelUseCase: LoadPlaylistForChannelUseCase
) : MvRxViewModel<DetailsState>(initialStateChannel) {

    companion object : MvRxViewModelFactory<DetailsState> {
        @JvmStatic
        override fun create(activity: FragmentActivity, stateChannel: DetailsState): BaseMvRxViewModel<DetailsState> {
            val loadPlaylistForChannelUseCase: LoadPlaylistForChannelUseCase by activity.inject()
            return DetailsViewModel(stateChannel, loadPlaylistForChannelUseCase)
        }
    }

    init {
        fetchPlaylist()
    }

    fun fetchPlaylist() = withState { state ->
        if (state.request is Loading) return@withState

        loadPlaylistForChannelUseCase(state.streamId)
            .execute { copy(request = it, playlist = it() ?: Playlist()) }
    }
}