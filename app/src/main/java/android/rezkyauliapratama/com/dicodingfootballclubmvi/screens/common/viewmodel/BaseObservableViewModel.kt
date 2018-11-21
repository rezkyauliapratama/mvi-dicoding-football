package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.viewmodel

import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviIntent
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviState
import androidx.lifecycle.ViewModel
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error


abstract class BaseObservableViewModel<I : MviIntent, S: MviState> :  BaseViewModel(), MviViewModel<I, S>,AnkoLogger {

    protected val liveData: MutableLiveData<S> = MutableLiveData()

    override fun getLiveData(): LiveData<S> = liveData

}

