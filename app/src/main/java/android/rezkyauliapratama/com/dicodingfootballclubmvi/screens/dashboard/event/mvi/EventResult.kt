package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.mvi

import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model.Event
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviResult

sealed class EventResult: MviResult{
    sealed class LoadEventResult : EventResult() {
        data class Success(val events: MutableList<Event>) : LoadEventResult()
        data class Failure(val error: Throwable) : LoadEventResult()
        object InFlight : LoadEventResult()
    }
}