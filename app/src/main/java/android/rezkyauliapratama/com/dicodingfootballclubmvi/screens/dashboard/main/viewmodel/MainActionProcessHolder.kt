package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel

import android.rezkyauliapratama.com.dicodingfootballclubmvi.common.rx.BaseSchedulerProvider
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainAction
import android.rezkyauliapratama.com.dicodingfootballclubmvi.usecase.TeamUseCase
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class MainActionProcessHolder (var teamUseCase: TeamUseCase, private val schedulerProvider: BaseSchedulerProvider){

}