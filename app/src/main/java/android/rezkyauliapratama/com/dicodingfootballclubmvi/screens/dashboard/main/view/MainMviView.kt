package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.view

import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.model.Team
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.views.ObservableMviView


interface  MainMviView : ObservableMviView<MainMviView.Listener> {

    interface Listener {
        fun onSpinnerChanged(s:String)

    }


}