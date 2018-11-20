package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.adapter

import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model.Team
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.views.MviView


interface TeamAdapterMviView: MviView{


    fun bindList(team: Team)

}