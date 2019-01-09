package com.mvrxopenfm.ui.channels

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.fragmentViewModel
import com.mvrxopenfm.R
import com.mvrxopenfm.base.BaseFragment
import com.mvrxopenfm.base.simpleController
import com.mvrxopenfm.ui.details.DetailsStateArgs
import com.mvrxopenfm.ui.favoriteGroup.FavoriteViewModel
import com.mvrxopenfm.views.channelRow
import com.mvrxopenfm.views.groupRow

class ChannelFragment : BaseFragment() {

    private val viewModel: ChannelViewModel by fragmentViewModel()
    private val favoriteViewModel: FavoriteViewModel by activityViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.asyncSubscribe(ChannelGroupState::request, onFail = { error ->
            Snackbar.make(coordinatorLayout, "Request failed.", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry") {
                    viewModel.fetchChannels()
                }
                .show()
        })

        viewModel.fetchChannels()
    }

    override fun epoxyController() = simpleController(viewModel, favoriteViewModel) { state, favoriteState ->

        if(state.request is Loading)
            return@simpleController

        val allGroups = listOf(favoriteState.favoriteChannelsGroup) + state.channelsGroups

        allGroups.forEach { channelsGroup ->

            if(channelsGroup.channels.isEmpty())
                return@forEach

            groupRow {
                id("group ${channelsGroup.name}")
                name(channelsGroup.name)
                clickListener { _ -> viewModel.toggleExpand(channelsGroup.name) }
            }

            if (state.isGroupHidden(channelsGroup.name))
                return@forEach

            channelsGroup.channels.forEach { channel ->
                channelRow {
                    id("channel ${channel.name} ${channelsGroup.name}")
                    render(channel)
                    clickListener { _ -> navigateTo(R.id.action_channelFragmentEpoxy_to_detailsFragment,
                            DetailsStateArgs(channel.name, channel.streamId))
                    }
                }
            }
        }
    }
}