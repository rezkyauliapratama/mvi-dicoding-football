package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.adapter.event

import android.rezkyauliapratama.com.dicodingfootballclubmvi.BR
import android.rezkyauliapratama.com.dicodingfootballclubmvi.R
import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model.Event
import android.rezkyauliapratama.com.dicodingfootballclubmvi.databinding.ListItemEventBinding
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.ViewMvvmFactory
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.views.BaseObservableMviView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso

class EventAdapterMviViewImpl(inflater: LayoutInflater, parent: ViewGroup?, mvvmFactory: ViewMvvmFactory) : BaseObservableMviView<EventAdapterMviView.Listener>(),EventAdapterMviView {

    val binding: ListItemEventBinding = DataBindingUtil.inflate(inflater, R.layout.list_item_event, parent, false)

    init {
        dataBinding = binding

    }
    override fun bindItems(event: Event) {
        binding.setVariable(BR.event,event)
        binding.executePendingBindings()

    }
}