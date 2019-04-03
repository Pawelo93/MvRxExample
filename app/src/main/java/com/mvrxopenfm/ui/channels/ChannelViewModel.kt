package com.mvrxopenfm.ui.channels

import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.mvrxopenfm.addOrRemove
import com.mvrxopenfm.base.MvRxViewModel
import com.mvrxopenfm.domain.useCase.LoadChannelGroupsUseCase
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class ChannelViewModel @AssistedInject constructor(
    @Assisted initialState: ChannelGroupState,
    private val loadChannelGroupsUseCase: LoadChannelGroupsUseCase
) : MvRxViewModel<ChannelGroupState>(initialState) {

    fun fetchChannels() = withState { state ->
        if(state.request is Loading) return@withState

        loadChannelGroupsUseCase()
            .execute { copy(request = it, channelsGroups = it() ?: emptyList()) }
    }

    fun toggleExpand(channelGroupName: String) {
        setState { copy(hiddenGroups = hiddenGroups.addOrRemove(channelGroupName)) }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: ChannelGroupState): ChannelViewModel
    }

    companion object : MvRxViewModelFactory<ChannelViewModel, ChannelGroupState> {
        @JvmStatic
        override fun create(viewModelContext: ViewModelContext, state: ChannelGroupState): ChannelViewModel? {
            val fragment: ChannelFragment = (viewModelContext as FragmentViewModelContext).fragment()
            return fragment.channelViewModelFactory.create(state)
        }
    }
}