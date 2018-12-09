package com.mvrxopenfm.ui.favoriteGroup

import com.mvrxopenfm.base.MvRxViewModel
import com.mvrxopenfm.domain.model.Channel

class FavoriteViewModel(initialStateChannel: FavoriteGroupState) :
    MvRxViewModel<FavoriteGroupState>(initialStateChannel) {

    fun toggleFavorite(channel: Channel) {
        setState { copy(favoriteChannelsGroup = favoriteChannelsGroup.addOrRemoveChannel(channel)) }
    }
}