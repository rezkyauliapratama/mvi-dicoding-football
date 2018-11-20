package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel

import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.viewmodel.BaseViewModel
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.ReducerFactory
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainAction
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainAction.LoadMainAction
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainIntent
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainState
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import javax.inject.Inject


class MainViewModel @Inject constructor(private val mainActionProcessHolder: MainActionProcessHolder, private val reducerFactory: ReducerFactory) : BaseViewModel<MainIntent, MainState>(),
    AnkoLogger {


    /**
     * Proxy subject used to keep the stream alive even after the UI gets recycled.
     * This is basically used to keep ongoing events and the last cached State alive
     * while the UI disconnects and reconnects on config changes.
     */
    private val intentsSubject: PublishSubject<MainIntent> = PublishSubject.create()
    private val statesObservable: Observable<MainState> = compose()

    init {
        error { "initviewmodel" }
        states().subscribe { liveData.value = it }.track()
    }
    /**
     * Compose all components to create the stream logic
     */
    private fun compose(): Observable<MainState> {
        return intentsSubject
            .compose<MainIntent>(intentFilter)
            .map<MainAction>(this::actionFromIntent)
            .compose(mainActionProcessHolder.actionProcessor)
            // Cache each state and pass it to the reducer to create a new state from
            // the previous cached one and the latest Result emitted from the action processor.
            // The Scan operator is used here for the caching.
            .scan(
                MainState.idle(),
                reducerFactory.mainReducer
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
    private fun actionFromIntent(intent: MainIntent): MainAction {
        return when (intent) {
            is MainIntent.InitialIntent -> LoadMainAction
        }
    }


    /**
     * take only the first ever InitialIntent and all intents of other types
     * to avoid reloading data on config changes
     */
    private val intentFilter: ObservableTransformer<MainIntent, MainIntent>
        get() = ObservableTransformer { intents ->
            intents.publish { shared ->
                Observable.merge<MainIntent>(
                    shared.ofType(MainIntent.InitialIntent::class.java).take(1),
                    shared.filter { !MainIntent.InitialIntent::class.java.isInstance(it) }
                )
            }
        }

    override fun processIntents(intents: Observable<MainIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<MainState> = statesObservable


}
