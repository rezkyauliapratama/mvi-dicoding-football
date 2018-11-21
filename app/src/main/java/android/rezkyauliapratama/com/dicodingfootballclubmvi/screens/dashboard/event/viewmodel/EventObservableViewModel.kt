package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.viewmodel

import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.ReducerFactory
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.viewmodel.BaseObservableViewModel
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.mvi.EventAction
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.mvi.EventAction.*
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.mvi.EventIntent
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.mvi.EventState
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.error
import javax.inject.Inject

class EventObservableViewModel @Inject constructor(private val eventActionProcssHolder: EventActionProcssHolder,
                                                   private val reducerFactory: ReducerFactory) : BaseObservableViewModel<EventIntent,EventState>(){


    /**
     * Proxy subject used to keep the stream alive even after the UI gets recycled.
     * This is basically used to keep ongoing events and the last cached State alive
     * while the UI disconnects and reconnects on config changes.
     */
    private val intentsSubject: BehaviorSubject<EventIntent> = BehaviorSubject.create()
    private val statesObservable: Observable<EventState> = compose()

    init {
        error { "initviewmodel" }
        states().subscribe { liveData.value = it }.track()
    }


    /**
     * Compose all components to create the stream logic
     */
    private fun compose(): Observable<EventState> {
        return intentsSubject
            .map<EventAction>(this::actionFromIntent)
            .compose(eventActionProcssHolder.actionProcessor)
            // Cache each state and pass it to the reducer to create a new state from
            // the previous cached one and the latest Result emitted from the action processor.
            // The Scan operator is used here for the caching.
            .scan(
                EventState.idle(),
                reducerFactory.eventReducer
            )
            // When a reducer just emits previousState, there's no reason to call render. In fact,
            // redrawing the UI in cases like this can cause jank (e.g. messing up snackbar animations
            // by showing the same snackbar twice in rapid succession).
            .distinctUntilChanged()
            // Emit the last one event of the stream on subscription.
            // Useful when a View rebinds to the ViewModel after rotation.
            .replay(1)
            // Create the stream on creation without waiting for anyone to subscribe
            // This allows the stream to stay alive even when the UI disconnects and
            // match the stream's lifecycle to the ViewModel's one.
            .autoConnect(0)
    }

    /**
     * Translate an [MviIntent] to an [MviAction].
     * Used to decouple the UI and the business logic to allow easy testings and reusability.
     */
    private fun actionFromIntent(intent: EventIntent): EventAction{
        return when (intent) {
            is EventIntent.LoadLeagueIntent -> {
                if (intent.isPast){
                    LoadPrevEventAction(intent.league)
                }else{
                    LoadNextEventAction(intent.league)
                }
            }
        }
    }


    override fun processIntents(intents: Observable<EventIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<EventState> = statesObservable

}