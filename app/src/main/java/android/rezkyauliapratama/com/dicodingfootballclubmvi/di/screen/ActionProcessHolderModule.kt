package android.rezkyauliapratama.com.dicodingfootballclubmvi.di.screen

import android.rezkyauliapratama.com.dicodingfootballclubmvi.common.TimeUtility
import android.rezkyauliapratama.com.dicodingfootballclubmvi.common.rx.SchedulerProvider
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.viewmodel.EventActionProcssHolder
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.viewmodel.MainActionProcessHolder
import android.rezkyauliapratama.com.dicodingfootballclubmvi.usecase.EventUseCase
import android.rezkyauliapratama.com.dicodingfootballclubmvi.usecase.TeamUseCase
import dagger.Module
import dagger.Provides


@Module
class ActionProcessHolderModule{

    @Provides
    fun getScheduler() : SchedulerProvider {
        return SchedulerProvider()
    }

    @Provides
    fun getMainActionProcessHolder(teamUseCase: TeamUseCase, schedulerProvider: SchedulerProvider) : MainActionProcessHolder {
        return MainActionProcessHolder(
            teamUseCase,
            schedulerProvider
        )
    }

    @Provides
    fun getEventActionProcessHolder(eventUseCase: EventUseCase, schedulerProvider: SchedulerProvider) : EventActionProcssHolder {
        return EventActionProcssHolder(
            eventUseCase,
            schedulerProvider)
    }


}