package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main

import android.rezkyauliapratama.com.dicodingfootballclubmvi.databinding.ActivityMainBinding
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.controller.BaseActivity
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.view.MainMviView
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.MainViewModel
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainIntent
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainState
import io.reactivex.Observable


class MainActivity : BaseActivity<MainViewModel, MainIntent, MainState, MainMviView, ActivityMainBinding>(),
    MainMviView.Listener {

    override fun onSpinnerChanged(s: String) {
        mViewModel.processSpinner(s)
    }


    override fun bind() {

    }

    override fun intents(): Observable<MainIntent> = Observable.never()


    override fun render(state: MainState) {
    }


    override fun registerListener() {
        mViewMvc.registerListener(this)
    }

    override fun unregisterListener() {
        mViewMvc.unregisterListener(this)

    }

    override fun initViewModel(): MainViewModel {
        return viewModelFactoryImpl.getMainViewModel(this)
    }

    override fun initDataBinding() {
        mDataBinding = mViewMvc.dataBinding as ActivityMainBinding
    }

    override fun inject() {
        initControllerComponent()?.inject(this)
    }

    override fun initView() {
        mViewMvc = viewMvvmFactory.getMainView(null)
    }



}
