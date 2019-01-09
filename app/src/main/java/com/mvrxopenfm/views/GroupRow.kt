package com.mvrxopenfm.views

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.mvrxopenfm.R

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class GroupRow @JvmOverloads constructor(context: Context) : LinearLayout(context) {

    private val groupName: TextView

    init {
        View.inflate(context, R.layout.group_row, this)
        groupName = findViewById(R.id.groupName)
    }

    @TextProp
    fun setName(text: CharSequence) {
        groupName.text = text
    }

    @CallbackProp
    fun setClickListener(clickListener: OnClickListener?) {
        setOnClickListener(clickListener)
    }

    /*
    or

    var listener: OnClickListener? = null
        @CallbackProp set

     */
}