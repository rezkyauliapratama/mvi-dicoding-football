package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi

import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model.Team
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviResult

sealed class MainResult : MviResult {
  sealed class LoadMainResult : MainResult() {
    data class Success(val teams: MutableList<Team>) : LoadMainResult()
    data class Failure(val error: Throwable) : LoadMainResult()
    object InFlight : LoadMainResult()
  }
}
