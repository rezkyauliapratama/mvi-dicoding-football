package android.rezkyauliapratama.com.dicodingfootballclubmvi.di.screen

import android.content.Context
import android.rezkyauliapratama.com.dicodingfootballclubmvi.di.viewmodel.ViewModelFactory
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.fragmentframehelper.FragmentFrameHelper
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.fragmentframehelper.FragmentFrameWrapper
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.screennavigator.ScreensNavigator
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.ViewMvvmFactory
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.ViewModelFactoryImpl

@Module
class MviWrapperModule{

    @Provides
    fun getViewMvvmFactory(@ActivityContext context: Context): ViewMvvmFactory {
        return ViewMvvmFactory(LayoutInflater.from(context))
    }

    @Provides
    fun getScreensNavigator(activity: FragmentActivity): ScreensNavigator {
        return ScreensNavigator(getFragmentFrameHelper(activity), activity)
    }


    @Provides
    fun getViewModel(viewModelFactory: ViewModelFactory): ViewModelFactoryImpl {
        return ViewModelFactoryImpl(
            viewModelFactory
        )
    }


    @Provides
    fun getFragmentManager(activity: FragmentActivity): FragmentManager {
        return activity.supportFragmentManager
    }

    @Provides
    fun getFragmentFrameHelper(activity: FragmentActivity): FragmentFrameHelper? {
        return FragmentFrameHelper(activity, getFragmentFrameWrapper(activity), getFragmentManager(activity))
    }

    @Provides
    fun getFragmentFrameWrapper(activity: FragmentActivity): FragmentFrameWrapper? {
        return if (activity is FragmentFrameWrapper)
            activity
        else
            null
    }

}