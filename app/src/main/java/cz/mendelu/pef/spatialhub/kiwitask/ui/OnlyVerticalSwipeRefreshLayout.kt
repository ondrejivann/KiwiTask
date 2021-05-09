package cz.mendelu.pef.spatialhub.kiwitask.ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * Common issue with horizontal recyclerview and vertical swipe refresh. Stackoverflow solution.
 * Source: https://stackoverflow.com/questions/34136178/swiperefreshlayout-blocking-horizontally-scrolled-recyclerview
 */
class OnlyVerticalSwipeRefreshLayout(context: Context, attrs: AttributeSet): SwipeRefreshLayout(
    context,
    attrs
) {

    private var touchSlop = 0
    private var prevX = 0f
    private var declined = false

    init {
        touchSlop = ViewConfiguration.get( context ).getScaledTouchSlop();
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                prevX = MotionEvent.obtain(event).x
                declined = false // New action
            }
            MotionEvent.ACTION_MOVE -> {
                val eventX = event.x
                val xDiff: Float = Math.abs(eventX - prevX)
                if (declined || xDiff > touchSlop) {
                    declined = true // Memorize
                    return false
                }
            }
        }
        return super.onInterceptTouchEvent(event)
    }
}