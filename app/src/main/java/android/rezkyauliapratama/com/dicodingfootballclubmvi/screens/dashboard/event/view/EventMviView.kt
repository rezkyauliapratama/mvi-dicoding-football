package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.view

import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model.Event
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.views.ObservableMviView

interface EventMviView : ObservableMviView<EventMviView.Listener>{

    interface Listener

    fun bindItems(response: MutableList<Event>)

    fun showProgressIndication()

    fun hideProgressIndication()

    fun showStatusIndication(message: String)

    fun hideStatusIndication()

    fun clearItem()


}