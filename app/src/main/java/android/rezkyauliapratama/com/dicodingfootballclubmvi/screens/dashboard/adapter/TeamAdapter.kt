package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.adapter

import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model.Team
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.ViewMvvmFactory
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class TeamAdapter(private val viewMvvmFactory: ViewMvvmFactory):
    RecyclerView.Adapter<TeamAdapter.ViewHolder>(){


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mViewMvc.bindList(mItems[position])
    }

    fun bindItems(teams: MutableList<Team>) {
        mItems.clear()
        if (!teams.isEmpty()){
            mItems.addAll(teams)
        }
        notifyDataSetChanged()
    }
    private val mItems: MutableList<Team> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewMvc = viewMvvmFactory.getTeamAdapterView(parent)
        return ViewHolder(viewMvc)
    }

    override fun getItemCount() = mItems.size

    fun removeData() {
        mItems.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(val mViewMvc: TeamAdapterMviView) : RecyclerView.ViewHolder(mViewMvc.dataBinding.root)

}