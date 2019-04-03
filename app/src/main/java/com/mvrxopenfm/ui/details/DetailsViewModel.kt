package com.mvrxopenfm.ui.details

import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.mvrxopenfm.base.MvRxViewModel
import com.mvrxopenfm.domain.model.Playlist
import com.mvrxopenfm.domain.useCase.LoadPlaylistForChannelUseCase
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class DetailsViewModel @AssistedInject constructor(
    @Assisted initialStateChannel: DetailsState,
    private val loadPlaylistForChannelUseCase: LoadPlaylistForChannelUseCase
) : MvRxViewModel<DetailsState>(initialStateChannel) {

    init {
        fetchPlaylist()
    }

    fun fetchPlaylist() = withState { state ->
        if (state.request is Loading) return@withState

        loadPlaylistForChannelUseCase(state.streamId)
            .execute { copy(request = it, playlist = it() ?: Playlist()) }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(initialStateChannel: DetailsState): DetailsViewModel
    }

    companion object : MvRxViewModelFactory<DetailsViewModel, DetailsState> {
        @JvmStatic
        override fun create(viewModelContext: ViewModelContext, state: DetailsState): DetailsViewModel? {
            val fragment: DetailsFragment = (viewModelContext as FragmentViewModelContext).fragment()
            return fragment.channelViewModelFactory.create(state)
        }
    }
}