package android.rezkyauliapratama.com.dicodingfootballclubmvi.di.screen

import dagger.Component
import android.rezkyauliapratama.com.dicodingfootballclubmvi.di.application.ApplicationComponent
import android.rezkyauliapratama.com.dicodingfootballclubmvi.di.viewmodel.ViewModelFactory
import android.rezkyauliapratama.com.dicodingfootballclubmvi.di.viewmodel.ViewModelModule
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.MainActivity

/**
 * Created by Rezky Aulia Pratama on 15/8/18.
 */
@PerScreen
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class, MviWrapperModule::class, UseCaseModule::class, ViewModelModule::class, ActionProcessHolderModule::class])
interface ScreenComponent{

    fun getViewModelFactory() : ViewModelFactory

    fun inject(loginActivity: MainActivity)
}