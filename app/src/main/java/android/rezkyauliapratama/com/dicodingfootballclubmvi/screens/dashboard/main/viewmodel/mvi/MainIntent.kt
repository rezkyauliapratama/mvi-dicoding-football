package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi

import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviIntent


sealed class MainIntent : MviIntent {
    data class SpinnerChanged(val s: String) : MainIntent()
}
