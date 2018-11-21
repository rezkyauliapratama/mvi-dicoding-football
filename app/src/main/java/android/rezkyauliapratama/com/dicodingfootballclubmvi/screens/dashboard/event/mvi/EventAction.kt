package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.mvi

import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviAction

sealed class EventAction: MviAction{
    data class LoadPrevEventAction(val s:String): EventAction()
    data class LoadNextEventAction(val s:String): EventAction()
}