package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.view

import android.rezkyauliapratama.com.dicodingfootballclubmvi.R
import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model.Team
import android.rezkyauliapratama.com.dicodingfootballclubmvi.databinding.ActivityMainBinding
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.ViewMvvmFactory
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.views.BaseObservableMviView
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.adapter.team.TeamAdapter
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.event.EventFragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.error
import org.jetbrains.anko.support.v4.ctx


class MainMviViewImpl(inflater: LayoutInflater, parent: ViewGroup?, viewMvvmFactory: ViewMvvmFactory) :
    BaseObservableMviView<MainMviView.Listener>(),
    MainMviView {


    var binding: ActivityMainBinding = DataBindingUtil.inflate(inflater, R.layout.activity_main, parent, false)

    val fragments: MutableList<Fragment> = mutableListOf()
    private lateinit var fragment: Fragment

    private lateinit var tabAdapter: LfPagerAdapter

    init {
        dataBinding = binding

        initTab()
        initViewPager()

        val spinnerItems = getContext().resources.getStringArray(R.array.league)
        val arrLeagueId =  getContext().resources.getStringArray(R.array.league_id)
        val spinnerAdapter = ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, spinnerItems)
        binding.spinnerEvent.adapter = spinnerAdapter

        binding.spinnerEvent.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val leagueId = arrLeagueId[position]
                for (listener in listeners){
                        listener.onSpinnerChanged(leagueId)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

    }


    private fun initTab() {
        val tabs = arrayOf(
            binding.tabLayoutEvent.newTab().setText("Previous Event"),
            binding.tabLayoutEvent.newTab().setText("Next Event")

        )

        for (tab in tabs) {
            val layout = LinearLayout(getContext())
            layout.orientation = LinearLayout.VERTICAL
            layout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layout.weightSum = 1f
            val newTab = TextView(getContext())
            newTab.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            newTab.gravity = Gravity.CENTER
            newTab.maxLines = 1
            newTab.text = tab.text

            getContext().let { ContextCompat.getColor(it, android.R.color.white) }.let { newTab.setTextColor(it) }

            layout.addView(newTab)

            tab.customView = layout
            tab.let { binding.tabLayoutEvent.addTab(it) }
        }

        binding.tabLayoutEvent.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                fragment = fragments[tab.position]
                binding.viewPagerEvent.currentItem = tab.position

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    private fun initViewPager() {
        fragments.add(EventFragment.newInstance(true))
        fragments.add(EventFragment.newInstance(false))

        fragment = fragments[0]
        this.tabAdapter = LfPagerAdapter((getContext() as FragmentActivity).supportFragmentManager, fragments)

        binding.viewPagerEvent.offscreenPageLimit = 3
        binding.viewPagerEvent.adapter = tabAdapter
        binding.viewPagerEvent.isPagingEnabled = false
        binding.viewPagerEvent.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayoutEvent))
    }

    class LfPagerAdapter (fm: FragmentManager, private val fragments:MutableList<Fragment>): FragmentStatePagerAdapter(fm)
    {

        private val NUMITEMS = 2



        override fun getCount(): Int {
            return NUMITEMS
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                1 -> fragments[1]
                else -> fragments[0]
            }
        }


    }

}

