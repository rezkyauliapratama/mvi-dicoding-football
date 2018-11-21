package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common

import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.adapter.event.EventAdapterMviView
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.adapter.event.EventAdapterMviViewImpl
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.view.MainMviView
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.view.MainMviViewImpl
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.adapter.team.TeamAdapterMviView
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.adapter.team.TeamAdapterMviViewImpl
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.view.EventMviView
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.view.EventMviViewImpl
import android.view.LayoutInflater
import android.view.ViewGroup

class ViewMvvmFactory(private val mLayoutInflater: LayoutInflater) {
    fun getEventAdapterView(parent: ViewGroup): EventAdapterMviView {
        return EventAdapterMviViewImpl(
            mLayoutInflater,
            parent,
            this
        )
    }

    fun getTeamAdapterView(parent: ViewGroup): TeamAdapterMviView {
        return TeamAdapterMviViewImpl(
            mLayoutInflater,
            parent,
            this
        )
    }

    fun getMainView(parent: ViewGroup?): MainMviView {
        return MainMviViewImpl(
            mLayoutInflater,
            parent,
            this
        )
    }


    fun getEventView(parent: ViewGroup?): EventMviView {
        return EventMviViewImpl(
            mLayoutInflater,
            parent,
            this
        )
    }



}
