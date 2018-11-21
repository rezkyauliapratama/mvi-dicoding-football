package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.viewmodel

import android.rezkyauliapratama.com.dicodingfootballclubmvi.common.TimeUtility
import android.rezkyauliapratama.com.dicodingfootballclubmvi.common.rx.BaseSchedulerProvider
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.mvi.EventAction
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.mvi.EventAction.*
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.mvi.EventResult
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.mvi.EventResult.LoadEventResult
import android.rezkyauliapratama.com.dicodingfootballclubmvi.usecase.EventUseCase
import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import java.lang.IllegalArgumentException

class EventActionProcssHolder(private val eventUseCase: EventUseCase,
                              private val schedulerProvider: BaseSchedulerProvider){


    private val loadNextEventProcessor =
            ObservableTransformer<LoadNextEventAction, LoadEventResult> {
                it.flatMap {
                    eventUseCase.getNextEvent(it.s)
                        .toObservable()
                        .map { events ->
                            LoadEventResult.Success(events)
                         }
                        .cast(LoadEventResult::class.java)
                        // Wrap any error into an immutable object and pass it down the stream
                        // without crashing.
                        // Because errors are data and hence, should just be part of the stream.
                        .onErrorReturn(LoadEventResult::Failure)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        // Emit an InFlight event to notify the subscribers (e.g. the UI) we are
                        // doing work and waiting on a response.
                        // We emit it after observing on the UI thread to allow the event to be emitted
                        // on the current frame and avoid jank.
                        .startWith(LoadEventResult.InFlight)
                }
            }
    private val loadPrevEventProcessor =
        ObservableTransformer<LoadPrevEventAction, LoadEventResult> { it ->
            it.flatMap {
                eventUseCase.getPastEvent(it.s)
                    .toObservable()
                    .map { events ->
                        LoadEventResult.Success(events)
                     }
                    .cast(LoadEventResult::class.java)
                    // Wrap any error into an immutable object and pass it down the stream
                    // without crashing.
                    // Because errors are data and hence, should just be part of the stream.
                    .onErrorReturn(LoadEventResult::Failure)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    // Emit an InFlight event to notify the subscribers (e.g. the UI) we are
                    // doing work and waiting on a response.
                    // We emit it after observing on the UI thread to allow the event to be emitted
                    // on the current frame and avoid jank.
                    .startWith(LoadEventResult.InFlight)
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
        ObservableTransformer<EventAction, EventResult> { actions ->
            actions.publish { shared ->
               Observable.merge(
                   shared.ofType(EventAction.LoadPrevEventAction::class.java).compose(loadPrevEventProcessor),
                   shared.ofType(EventAction.LoadNextEventAction::class.java).compose(loadNextEventProcessor)
               )
                   .cast(EventResult::class.java)
                   .mergeWith(
                       shared.filter{
                           it !is EventAction.LoadNextEventAction && it !is EventAction.LoadPrevEventAction
                       }.flatMap {
                           Observable.error<EventResult>(
                               IllegalArgumentException("unknown Action type : $it")
                           )
                       }
                   )
            }
        }
}