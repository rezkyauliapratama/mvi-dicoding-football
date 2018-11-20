package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.viewmodel

import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviIntent
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviState
import androidx.lifecycle.ViewModel
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseViewModel<I : MviIntent, S: MviState> :  ViewModel(), MviViewModel<I, S> {

    private val compositeDisposable = CompositeDisposable()
    val liveData: MutableLiveData<S> = MutableLiveData()

    protected fun Disposable.track() {
        compositeDisposable.add(this)
    }

    override fun getLiveData(): LiveData<S> = liveData

    override fun cleanUp() {

        compositeDisposable.clear()
    }

    override fun onCleared() {
        super.onCleared()
        cleanUp()
    }
}

