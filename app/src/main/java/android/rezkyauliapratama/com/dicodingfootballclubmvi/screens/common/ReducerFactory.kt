package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common

import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.mvi.EventResult
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.mvi.EventState
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainResult
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainState
import com.google.gson.Gson
import io.reactivex.functions.BiFunction
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import javax.inject.Inject

class ReducerFactory @Inject constructor(): AnkoLogger {

    /**
     * The Reducer is where [MviViewState], that the [MviView] will use to
     * render itself, are created.
     * It takes the last cached [MviViewState], the latest [MviResult] and
     * creates a new [MviViewState] by only updating the related fields.
     * This is basically like a big switch statement of all possible types for the [MviResult]
     */

    val eventReducer = BiFunction { previousState: EventState, result: EventResult ->
        when (result) {
            is EventResult.LoadEventResult -> when (result) {
                is EventResult.LoadEventResult.Success ->{
                    val items = result.events
                    previousState.copy(
                        isLoading = false,
                        events = items
                    )
                }
                is EventResult.LoadEventResult.Failure -> previousState.copy(isLoading = false, error = result.error.localizedMessage)
                is EventResult.LoadEventResult.InFlight -> previousState.copy(isLoading = true)
            }
        }
    }
}
