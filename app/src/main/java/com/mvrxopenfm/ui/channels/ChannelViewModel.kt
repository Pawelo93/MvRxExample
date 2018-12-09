package com.mvrxopenfm.ui.channels

import android.support.v4.app.FragmentActivity
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxViewModelFactory
import com.mvrxopenfm.addOrRemove
import com.mvrxopenfm.base.MvRxViewModel
import com.mvrxopenfm.domain.useCase.LoadChannelGroupsUseCase
import org.koin.android.ext.android.inject

class ChannelViewModel(
    initialStateChannel: ChannelGroupState,
    private val loadChannelGroupsUseCase: LoadChannelGroupsUseCase
) :
    MvRxViewModel<ChannelGroupState>(initialStateChannel) {

    companion object : MvRxViewModelFactory<ChannelGroupState> {
        @JvmStatic
        override fun create(
            activity: FragmentActivity,
            stateChannel: ChannelGroupState
        ): BaseMvRxViewModel<ChannelGroupState> {
            val loadChannelGroupsUseCase: LoadChannelGroupsUseCase by activity.inject()
            return ChannelViewModel(stateChannel, loadChannelGroupsUseCase)
        }
    }

    fun fetchChannels() = withState { state ->
        if (state.request is Loading) return@withState

        loadChannelGroupsUseCase()
            .execute { copy(request = it, channelsGroups = it() ?: emptyList()) }
    }

    fun toggleExpand(channelGroupName: String) {
        setState { copy(hiddenGroups = hiddenGroups.addOrRemove(channelGroupName)) }
    }
}