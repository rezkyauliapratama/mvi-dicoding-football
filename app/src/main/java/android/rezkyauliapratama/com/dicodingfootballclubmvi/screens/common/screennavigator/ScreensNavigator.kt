package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.screennavigator

import android.app.Activity
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.dashboard.main.MainActivity
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.fragmentframehelper.FragmentFrameHelper



class ScreensNavigator(private val fragmentFrameHelper: FragmentFrameHelper?, private val activity: Activity) {

    fun toMainActivity(){
        activity.ctx.startActivity<MainActivity>()
        activity.finish()
    }
/*

    fun toOwenerLoginActivity() {
        activity.ctx.startActivity<OwnerLoginActivity>()
        activity.finish()
    }
*/

    /*  fun toLastEvent() {
          mFragmentFrameHelper.replaceFragmentAndClearBackstack(LastEventFragment.newInstance())
      }

      fun fetchDataIntoLastEvent(s : String){
          (mFragmentFrameHelper.getFragment() as LastEventFragment).setData(s)
      }*/
}
