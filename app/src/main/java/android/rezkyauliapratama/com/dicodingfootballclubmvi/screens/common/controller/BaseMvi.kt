package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.controller

import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviIntent
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviState
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviViewModel
import io.reactivex.Observable

interface BaseMvi <I : MviIntent, in S : MviState> {
    /**
     * Unique [Observable] used by the [MviViewModel]
     * to listen to the [MviView].
     * All the [MviView]'s [MviIntent]s must go through this [Observable].
     */
    fun intents(): Observable<I>

    /**
     * Entry point for the [MviView] to render itself based on a [MviState].
     */
    fun render(state: S)

    fun bind()
}
