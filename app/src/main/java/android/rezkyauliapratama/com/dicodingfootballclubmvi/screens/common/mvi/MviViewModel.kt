package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi

import androidx.lifecycle.LiveData
import io.reactivex.Observable

/**
 * Object that will subscribes to a [MviView]'s [MviIntent]s,
 * process it and emit a [MviState] back.
 *
 * @param I Top class of the [MviIntent] that the [MviViewModel] will be subscribing
 * to.
 * @param S Top class of the [MviState] the [MviViewModel] will be emitting.
 */
interface MviViewModel<I : MviIntent, S : MviState> {
  fun processIntents(intents: Observable<I>)

  fun states(): Observable<S>

  fun getLiveData(): LiveData<S>

}
