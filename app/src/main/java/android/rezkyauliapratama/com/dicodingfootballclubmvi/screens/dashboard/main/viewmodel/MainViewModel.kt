package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel

import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.ReducerFactory
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.viewmodel.BaseViewModel
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainIntent
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject


class MainViewModel @Inject constructor() : BaseViewModel() {

    val spinnerLD: MediatorLiveData<MainIntent> = MediatorLiveData()
    init {
        Log.e("MainViewModel","init mainviewmodel")
    }

    fun processSpinner(s: String) {
        Log.e("MainViewModel",s)
        spinnerLD.value = MainIntent.SpinnerChanged(s)
    }


}
