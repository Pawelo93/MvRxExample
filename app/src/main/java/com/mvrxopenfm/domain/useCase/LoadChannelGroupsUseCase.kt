package com.mvrxopenfm.domain.useCase

import com.mvrxopenfm.data.model.ChannelsGroupResponse
import com.mvrxopenfm.data.network.ApiService
import com.mvrxopenfm.domain.model.Channel
import com.mvrxopenfm.domain.model.ChannelsGroup
import io.reactivex.Single
import javax.inject.Inject

class LoadChannelGroupsUseCase @Inject constructor(private val apiService: ApiService) {

    operator fun invoke(): Single<List<ChannelsGroup>> {
        return apiService.loadChannels()
            .map(::mapper)
    }

    private fun mapper(response: ChannelsGroupResponse): List<ChannelsGroup> {
        val groups = mutableListOf<ChannelsGroup>()
        response.wsGroupResponses.forEach { group ->
            val channels = mutableListOf<Channel>()
            group.streamIds.forEach { groupStreamId ->
                response.wsChannelResponses.forEach { channelResponse ->
                    if (groupStreamId == channelResponse.streamId)
                        channels.add(Channel(channelResponse.name ?: "no-name", channelResponse.streamId))
                }
            }
            groups.add(ChannelsGroup(group.name, channels))
        }
        return groups
    }
}