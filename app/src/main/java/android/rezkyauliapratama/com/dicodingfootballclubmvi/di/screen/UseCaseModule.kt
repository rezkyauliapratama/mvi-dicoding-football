package android.rezkyauliapratama.com.dicodingfootballclubmvi.di.screen


import android.rezkyauliapratama.com.dicodingfootballclubmvi.common.TimeUtility
import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.DataManager
import android.rezkyauliapratama.com.dicodingfootballclubmvi.usecase.EventUseCase
import android.rezkyauliapratama.com.dicodingfootballclubmvi.usecase.TeamUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule{


    @Provides
    fun getTimeUtility(): TimeUtility{
        return TimeUtility()
    }

    @Provides
    fun getTeamUseCase(dataManager: DataManager) : TeamUseCase{
        return TeamUseCase(dataManager)
    }

    @Provides
    fun getEventUseCase(dataManager: DataManager, timeUtility: TimeUtility) : EventUseCase{
        return EventUseCase(dataManager,timeUtility)
    }

}