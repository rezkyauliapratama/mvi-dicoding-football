package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.mvi

import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model.Event
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviState

data class EventState(
    val isLoading : Boolean,
    val events: MutableList<Event>,
    val error : String?
): MviState{
    companion object {
        fun idle(): EventState{
            return EventState(
                isLoading = false,
                events = mutableListOf(),
                error = null
            )
        }
    }
}