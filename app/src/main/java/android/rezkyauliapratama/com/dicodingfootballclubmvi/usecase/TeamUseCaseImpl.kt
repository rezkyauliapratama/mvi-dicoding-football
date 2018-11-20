package android.rezkyauliapratama.com.dicodingfootballclubmvi.usecase

import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.DataManager
import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model.Team
import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.network.NetworkApi
import com.google.gson.Gson
import io.reactivex.ObservableSource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class TeamUseCaseImpl @Inject constructor(private val dataManager: DataManager) :
    AnkoLogger{


    fun getTeamSingle(): Single<MutableList<Team>>{
        return dataManager.networkApi
            .getSearchAllTeams("English Premier League").subscribeOn(Schedulers.io())
            .flatMap{t ->
                Single.just(t.teams)
            }

    }
}