package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.adapter.team

import android.rezkyauliapratama.com.dicodingfootballclubmvi.BR
import android.rezkyauliapratama.com.dicodingfootballclubmvi.R
import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model.Team
import android.rezkyauliapratama.com.dicodingfootballclubmvi.databinding.ListItemTeamBinding
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.ViewMvvmFactory
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.views.BaseMviView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso


class TeamAdapterMviViewImpl(inflater: LayoutInflater, parent: ViewGroup?, viewMvvmFactory: ViewMvvmFactory) :
    BaseMviView(),
    TeamAdapterMviView {


    var binding: ListItemTeamBinding = DataBindingUtil.inflate(inflater, R.layout.list_item_team,parent,false)

    init {
        dataBinding = binding
    }

    override fun bindList(team: Team) {

            Picasso
                .get()
                .load(team.teamBadge)
                .into(binding.ivImage)


        binding.setVariable(BR.item,team)
        binding.executePendingBindings()
    }

}