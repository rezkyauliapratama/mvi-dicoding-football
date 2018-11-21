package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common

import android.rezkyauliapratama.com.dicodingfootballclubmvi.di.viewmodel.ViewModelFactory
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.EventFragment
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.viewmodel.EventObservableViewModel
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.MainActivity
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.MainViewModel
import androidx.lifecycle.ViewModelProviders
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

class ViewModelFactoryImpl @Inject constructor(private val viewModelFactory: ViewModelFactory): AnkoLogger{

    fun getMainViewModel(mainActivity: MainActivity): MainViewModel {
        return ViewModelProviders.of(mainActivity, viewModelFactory).get(MainViewModel::class.java)
    }

    fun getEventViewModel(eventFragment: EventFragment): EventObservableViewModel {
        return ViewModelProviders.of(eventFragment,viewModelFactory).get(EventObservableViewModel::class.java)
    }

}
