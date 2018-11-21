package android.rezkyauliapratama.com.dicodingfootballclubmvi.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class CustomViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {
    var isPagingEnabled: Boolean = false

    init {
        this.isPagingEnabled = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (isPagingEnabled) super.onTouchEvent(event) else false
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (isPagingEnabled) super.onInterceptTouchEvent(event) else false
    }

}
