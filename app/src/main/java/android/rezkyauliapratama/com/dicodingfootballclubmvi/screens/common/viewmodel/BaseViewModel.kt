package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.AnkoLogger

abstract class BaseViewModel : ViewModel(), AnkoLogger{

    private val compositeDisposable = CompositeDisposable()


    protected fun Disposable.track() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()    }
}