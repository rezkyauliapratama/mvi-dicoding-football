package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.view

import android.rezkyauliapratama.com.dicodingfootballclubmvi.R
import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model.Team
import android.rezkyauliapratama.com.dicodingfootballclubmvi.databinding.ActivityMainBinding
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.ViewMvvmFactory
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.views.BaseObservableMviView
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.adapter.TeamAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.jetbrains.anko.error


class MainMviViewImpl(inflater: LayoutInflater, parent: ViewGroup?, viewMvvmFactory: ViewMvvmFactory) :
    BaseObservableMviView<MainMviView.Listener>(),
    MainMviView {

    override fun clearItem() {
        adapter.removeData()
    }


    var binding: ActivityMainBinding = DataBindingUtil.inflate(inflater, R.layout.activity_main, parent, false)
    private var adapter: TeamAdapter

    init {
        dataBinding = binding

        adapter = TeamAdapter(viewMvvmFactory)
        binding.rvTeam.layoutManager = LinearLayoutManager(getContext())
        binding.rvTeam.adapter = adapter

        binding.swipeTeam.isEnabled = false



    }

    override fun bindItems(response: MutableList<Team>) {

        adapter.bindItems(response)

    }

    override fun showProgressIndication() {
        binding.swipeTeam.isRefreshing = true
    }

    override fun hideProgressIndication() {
        binding.swipeTeam.isRefreshing = false

    }

    override fun showStatusIndication(message: String) {
        error { "error : $message" }
        adapter.removeData()
    }

    override fun hideStatusIndication() {

    }
}

