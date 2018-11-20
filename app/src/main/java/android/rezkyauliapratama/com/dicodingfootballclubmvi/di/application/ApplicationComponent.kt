package android.rezkyauliapratama.com.dicodingfootballclubmvi.di.application

import android.rezkyauliapratama.com.dicodingfootballclubmvi.BaseApplication
import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.DataManager
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent{

    fun getDataManager(): DataManager
    fun inject(baseApplication: BaseApplication)


}