package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel

import android.rezkyauliapratama.com.dicodingfootballclubmvi.common.rx.BaseSchedulerProvider
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainAction
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainAction.LoadMainAction
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainResult
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainResult.LoadMainResult
import android.rezkyauliapratama.com.dicodingfootballclubmvi.usecase.TeamUseCaseImpl
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class MainActionProcessHolder (var teamUseCase: TeamUseCaseImpl, private val schedulerProvider: BaseSchedulerProvider){


    private val loadResultProcessor =
        ObservableTransformer<LoadMainAction, LoadMainResult> { actions ->
            actions.flatMap {
                teamUseCase.getTeamSingle()
                    // Transform one event of a List<Task> to an observable<Task>.
                    .toObservable()
                    .map { tasks -> LoadMainResult.Success(tasks) }
                    .cast(LoadMainResult::class.java)
                    // Wrap any error into an immutable object and pass it down the stream
                    // without crashing.
                    // Because errors are data and hence, should just be part of the stream.
                    .onErrorReturn(LoadMainResult::Failure)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    // Emit an InFlight event to notify the subscribers (e.g. the UI) we are
                    // doing work and waiting on a response.
                    // We emit it after observing on the UI thread to allow the event to be emitted
                    // on the current frame and avoid jank.
                    .startWith(LoadMainResult.InFlight)
            }
        }

    /**
     * Splits the [Observable] to match each type of [MviAction] to
     * its corresponding business logic processor. Each processor takes a defined [MviAction],
     * returns a defined [MviResult]
     * The global actionProcessor then merges all [Observable] back to
     * one unique [Observable].
     *
     *
     * The splitting is done using [Observable.publish] which allows almost anything
     * on the passed [Observable] as long as one and only one [Observable] is returned.
     *
     *
     * An security layer is also added for unhandled [MviAction] to allow early crash
     * at runtime to easy the maintenance.
     */
    var actionProcessor =
        ObservableTransformer<MainAction, MainResult> { actions ->
            actions.publish { shared ->
                // Match LoadStatisticsResult to loadStatisticsProcessor
                shared.ofType(LoadMainAction::class.java).compose(loadResultProcessor)
                    .cast(MainResult::class.java)
                    .mergeWith(
                        // Error for not implemented actions
                        shared.filter { v -> v !is LoadMainAction }
                            .flatMap { w ->
                                Observable.error<MainResult>(
                                    IllegalArgumentException("Unknown Action type: " + w))
                            })
            }
        }
}