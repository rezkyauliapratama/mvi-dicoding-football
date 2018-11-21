package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.mvi

import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviIntent

sealed class EventIntent : MviIntent{
    data class LoadLeagueIntent(val league: String,val isPast: Boolean) : EventIntent()
}