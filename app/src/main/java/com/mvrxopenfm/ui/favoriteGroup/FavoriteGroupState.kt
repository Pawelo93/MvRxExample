package com.mvrxopenfm.ui.favoriteGroup

import com.airbnb.mvrx.MvRxState
import com.mvrxopenfm.domain.model.ChannelsGroup

data class FavoriteGroupState(
    val favoriteChannelsGroup: ChannelsGroup = ChannelsGroup(name = "Ulubione")
) : MvRxState