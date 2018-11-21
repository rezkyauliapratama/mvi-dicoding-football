package android.rezkyauliapratama.com.dicodingfootballclubmvi.common

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

class TimeUtility {


    fun getUserFriendlyDate(date: Date): String {
        val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy")
        return simpleDateFormat.format(date)
    }

    fun convertStringToDate(str: String?): Date {
        val format = SimpleDateFormat("dd/MM/yy")
        return format.parse(str)

    }


}
