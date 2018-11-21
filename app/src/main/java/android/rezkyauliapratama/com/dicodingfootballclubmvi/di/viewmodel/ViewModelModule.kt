package android.rezkyauliapratama.com.dicodingfootballclubmvi.di.viewmodel

import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.viewmodel.EventObservableViewModel
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.MainViewModel
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventObservableViewModel::class)
    abstract fun bindEventViewModel(eventViewModel: EventObservableViewModel) : ViewModel


}