package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi

import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model.Team
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviState


data class MainState(
    val isLoading: Boolean,
    val tasks: MutableList<Team>,
    val error: String?
) : MviState {

    companion object {
        fun idle(): MainState {
            return MainState(
                isLoading = false,
                tasks = mutableListOf(),
                error = null
            )
        }
    }
}
