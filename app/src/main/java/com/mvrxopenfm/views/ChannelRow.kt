package com.mvrxopenfm.views

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.mvrxopenfm.R
import com.mvrxopenfm.domain.model.Channel
import kotlinx.android.synthetic.main.channel_row.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ChannelRow @JvmOverloads constructor(context: Context) : LinearLayout(context) {

    init {
        View.inflate(context, R.layout.channel_row, this)
    }

    @ModelProp
    fun render(channel: Channel) {
        channelNameTextView.text = channel.name
        streamIdTextView.text = "StreamId ${channel.streamId}"
    }

    @CallbackProp
    fun setClickListener(clickListener: OnClickListener?) {
        setOnClickListener(clickListener)
    }
}