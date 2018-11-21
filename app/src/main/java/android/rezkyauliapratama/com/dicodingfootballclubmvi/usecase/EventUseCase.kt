package android.rezkyauliapratama.com.dicodingfootballclubmvi.usecase

import android.rezkyauliapratama.com.dicodingfootballclubmvi.common.TimeUtility
import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.DataManager
import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model.Event
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import javax.inject.Inject

class EventUseCase @Inject constructor(private val dataManager: DataManager,private val timeUtility: TimeUtility): AnkoLogger{

    fun getPastEvent(s: String): Single<MutableList<Event>> {
        return dataManager.networkApi
            .getEventPastLeague(s)
            .map {
                for(event in it.events){
                    val date = timeUtility.convertStringToDate(event.strDate)
                    val strDate =  timeUtility.getUserFriendlyDate(date)
                    event.strDate = strDate
                }
                it.events
            }
    }

    fun getNextEvent(s: String): Single<MutableList<Event>> {
        return dataManager.networkApi
            .getEventNextLeague(s)
            .map {
                for(event in it.events){
                    val date = timeUtility.convertStringToDate(event.strDate)
                    val strDate =  timeUtility.getUserFriendlyDate(date)
                    event.strDate = strDate
                }
                it.events
            }
    }
}