package android.rezkyauliapratama.com.dicodingfootballclubmvi.data

import android.rezkyauliapratama.com.dicodingfootballclubmvi.data.network.NetworkApi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Rezky Aulia Pratama on 6/8/18.
 */
@Singleton
class DataManager @Inject constructor(){

    @Inject
    lateinit var networkApi: NetworkApi

}
