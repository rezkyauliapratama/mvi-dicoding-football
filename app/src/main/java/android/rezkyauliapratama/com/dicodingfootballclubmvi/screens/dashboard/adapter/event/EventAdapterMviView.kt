package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.adapter.event

import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model.Event
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.views.ObservableMviView

interface EventAdapterMviView : ObservableMviView<EventAdapterMviView.Listener>{

    interface Listener {
        fun onEventClicked(s: String)
    }

    fun bindItems(events : Event)
}