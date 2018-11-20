package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common

import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.view.MainMviView
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.view.MainMviViewImpl
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.adapter.TeamAdapterMviView
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.adapter.TeamAdapterMviViewImpl
import android.view.LayoutInflater
import android.view.ViewGroup

class ViewMvvmFactory(private val mLayoutInflater: LayoutInflater) {
    fun getTeamAdapterView(parent: ViewGroup): TeamAdapterMviView {
        return TeamAdapterMviViewImpl(mLayoutInflater, parent, this)
    }

    fun getMainView(parent: ViewGroup?): MainMviView {
        return MainMviViewImpl(
            mLayoutInflater,
            parent,
            this
        )
    }

/*
    fun getLoginViewMvc(parent: ViewGroup?): LoginViewMvc {
        return LoginViewMvcImpl(mLayoutInflater, parent, this)
    }
*//*

    fun getLandingPageView(parent: ViewGroup?): LandingPageView {
        return LandingPageViewImpl(
                mLayoutInflater,
                parent,
                this
        )

    }

    fun getOwnerLoginView(parent: ViewGroup?): OwnerLoginView {
        return OwnerLoginViewImpl(mLayoutInflater,parent,this)
    }*/

}
