package android.rezkyauliapratama.com.dicodingfootballclubmvi.di.screen


import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.DataManager
import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.network.NetworkApi
import android.rezkyauliapratama.com.dicodingfootballclubmvi.usecase.TeamUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule{

    @Provides
    fun getTeamUseCase(dataManager: DataManager) : TeamUseCaseImpl{
        return TeamUseCaseImpl(dataManager)
    }

}