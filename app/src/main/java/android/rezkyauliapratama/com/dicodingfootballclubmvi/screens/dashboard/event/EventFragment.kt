package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event

import android.os.Bundle
import android.rezkyauliapratama.com.dicodingfootballclubmvi.common.livedata.observe
import android.rezkyauliapratama.com.dicodingfootballclubmvi.databinding.FragmentEventBinding
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.controller.BaseFragment
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.mvi.EventIntent
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.mvi.EventState
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.view.EventMviView
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.viewmodel.EventObservableViewModel
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.MainActivity
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.MainViewModel
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainAction
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.mvi.MainIntent
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.error

class EventFragment : BaseFragment<EventObservableViewModel,EventIntent,EventState,EventMviView,FragmentEventBinding>(),
        EventMviView.Listener{

    companion object {
        const val ARGS_TYPE = "TYPE"

        fun newInstance (isPast : Boolean): EventFragment {
            val fragment = EventFragment()
            val args = Bundle()
            args.putBoolean(ARGS_TYPE, isPast)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var mainViewModel : MainViewModel

    private val argumentType: Boolean
        get() = arguments?.getBoolean(ARGS_TYPE) ?: false

    private val changeLeagueIntentPublisher = PublishSubject.create<EventIntent.LoadLeagueIntent>()

    override fun inject() {
        initControllerComponent()?.inject(this)
    }

    override fun initView(container: ViewGroup?) {
        mViewMvc = viewMvvmFactory.getEventView(container)

    }

    override fun initDataBinding() {
        mDataBinding = mViewMvc.dataBinding as FragmentEventBinding
    }

    override fun registerListener() {
        mViewMvc.registerListener(this)
    }

    override fun unregisterListener() {
        mViewMvc.unregisterListener(this)
    }

    override fun initViewModel(): EventObservableViewModel {
        mainViewModel =  viewModelFactoryImpl.getMainViewModel(requireActivity() as MainActivity)
        return viewModelFactoryImpl.getEventViewModel(this)
    }

    override fun intents(): Observable<EventIntent> = spinnerIntentObservable().cast(EventIntent::class.java)

    override fun render(state: EventState) {
        if (state.isLoading) mViewMvc.showProgressIndication()
        else mViewMvc.hideProgressIndication()


        if (state.error == null && !state.isLoading) {
            mViewMvc.bindItems(state.events)
        }
    }

    override fun bind() {
        observe(mViewModel.getLiveData()) { render(it) }

        observe(mainViewModel.spinnerLD){
            when (it){
                is MainIntent.SpinnerChanged -> {
                    changeLeagueIntentPublisher.onNext(
                        EventIntent.LoadLeagueIntent(it.s,argumentType)
                    )
                }
            }

        }
        // Pass the UI's intents to the ViewModel
        mViewModel.processIntents(intents())

    }

    private fun spinnerIntentObservable(): Observable<EventIntent.LoadLeagueIntent> {
        return changeLeagueIntentPublisher
    }
}