package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.adapter.event

import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model.Event
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.ViewMvvmFactory
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class EventAdapter(private val viewMvvmFactory: ViewMvvmFactory):
    RecyclerView.Adapter<EventAdapter.ViewHolder>(){


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mViewMvc.bindItems(mItems[position])
    }

    fun bindItems(events: MutableList<Event>) {
        mItems.clear()
        if (!events.isEmpty()){
            mItems.addAll(events)
        }
        notifyDataSetChanged()
    }
    private val mItems: MutableList<Event> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewMvc = viewMvvmFactory.getEventAdapterView(parent)
        return ViewHolder(
            viewMvc
        )
    }

    override fun getItemCount() = mItems.size

    fun removeData() {
        mItems.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(val mViewMvc: EventAdapterMviView) : RecyclerView.ViewHolder(mViewMvc.dataBinding.root)

}