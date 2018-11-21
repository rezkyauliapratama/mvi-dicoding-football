package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.view

import android.rezkyauliapratama.com.dicodingfootballclubmvi.R
import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model.Event
import android.rezkyauliapratama.com.dicodingfootballclubmvi.databinding.FragmentEventBinding
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.ViewMvvmFactory
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.views.BaseObservableMviView
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.adapter.event.EventAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager


class EventMviViewImpl(inflater: LayoutInflater, parent: ViewGroup?, viewMvvmFactory: ViewMvvmFactory) :
        BaseObservableMviView<EventMviView.Listener>(),
    EventMviView {

    val binding : FragmentEventBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_event,parent,false)
    var adapter : EventAdapter

    init {
        dataBinding = binding

        binding.swipeEvent.isEnabled = false


        adapter = EventAdapter(
            viewMvvmFactory
        )
        binding.rvEvent.layoutManager = LinearLayoutManager(getContext())
        binding.rvEvent.adapter = adapter


    }
    override fun bindItems(response: MutableList<Event>) {
        adapter.bindItems(response)
    }

    override fun showProgressIndication() {
        binding.swipeEvent.isRefreshing = true

    }

    override fun hideProgressIndication() {
        binding.swipeEvent.isRefreshing = false
    }

    override fun showStatusIndication(message: String) {

    }

    override fun hideStatusIndication() {

    }

    override fun clearItem() {
        adapter.removeData()
    }


}