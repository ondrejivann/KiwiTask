package cz.mendelu.pef.spatialhub.kiwitask.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import coil.load
import cz.mendelu.pef.spatialhub.kiwitask.databinding.FlightListItemLayoutBinding

class FlightListItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = FlightListItemLayoutBinding.inflate(LayoutInflater.from(context))

}