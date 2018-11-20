package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main

import android.os.Bundle
import android.rezkyauliapratama.com.dicodingfootballclubmvi.common.livedata.observe
import android.rezkyauliapratama.com.dicodingfootballclubmvi.databinding.ActivityMainBinding
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.controller.BaseActivity
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.viewmodel.BaseViewModel
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.view.MainMviView
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.MainViewModel
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainIntent
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainState
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.error


class MainActivity : BaseActivity<MainViewModel, MainIntent, MainState, MainMviView, ActivityMainBinding>(),
    MainMviView.Listener {


    override fun bind() {
        observe(mViewModel.getLiveData()) { render(it) }
        // Pass the UI's intents to the ViewModel
        mViewModel.processIntents(intents())
    }


    override fun onStop() {
        super.onStop()
        disposables.clear()
    }
    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun intents(): Observable<MainIntent> = initialIntent()

    private fun initialIntent(): Observable<MainIntent> {
        return Observable.just(MainIntent.InitialIntent)
    }

    override fun render(state: MainState) {
        if (state.isLoading) mViewMvc.showProgressIndication()
        else mViewMvc.hideProgressIndication()

        if (state.error != null) mViewMvc.showStatusIndication(state.error)
        else mViewMvc.hideStatusIndication()

        error { "state.task : ${Gson().toJson(state.tasks)}" }
        if (state.error == null && !state.isLoading) {
            mViewMvc.bindItems(state.tasks)
        }
    }

    override fun onSwipeRefresh() {

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
