package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common

import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainResult
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainState
import io.reactivex.functions.BiFunction
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

class ReducerFactory @Inject constructor(): AnkoLogger {

    /**
     * The Reducer is where [MviViewState], that the [MviView] will use to
     * render itself, are created.
     * It takes the last cached [MviViewState], the latest [MviResult] and
     * creates a new [MviViewState] by only updating the related fields.
     * This is basically like a big switch statement of all possible types for the [MviResult]
     */

    val mainReducer = BiFunction { previousState: MainState, result: MainResult ->
        when (result) {
            is MainResult.LoadMainResult -> when (result) {
                is MainResult.LoadMainResult.Success ->{
                    val tasks = result.teams
                    previousState.copy(
                        isLoading = false,
                        tasks = tasks
                    )
                }
                is MainResult.LoadMainResult.Failure -> previousState.copy(isLoading = false, error = result.error.localizedMessage)
                is MainResult.LoadMainResult.InFlight -> previousState.copy(isLoading = true)
            }
        }
    }
}
