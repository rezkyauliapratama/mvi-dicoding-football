package android.rezkyauliapratama.com.dicodingfootballclubmvi.di.screen

import android.rezkyauliapratama.com.dicodingfootballclubmvi.common.rx.SchedulerProvider
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.MainActionProcessHolder
import android.rezkyauliapratama.com.dicodingfootballclubmvi.usecase.TeamUseCaseImpl
import dagger.Module
import dagger.Provides


@Module
class ActionProcessHolderModule{

    @Provides
    fun getScheduler() : SchedulerProvider {
        return SchedulerProvider()
    }

    @Provides
    fun getMainActionProcessHolder(teamUseCase: TeamUseCaseImpl, schedulerProvider: SchedulerProvider) : MainActionProcessHolder {
        return MainActionProcessHolder(
            teamUseCase,
            schedulerProvider
        )
    }


}