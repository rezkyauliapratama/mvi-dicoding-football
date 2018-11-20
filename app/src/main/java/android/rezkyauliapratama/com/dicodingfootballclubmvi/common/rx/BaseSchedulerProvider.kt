package android.rezkyauliapratama.com.dicodingfootballclubmvi.common.rx

import io.reactivex.Scheduler

interface BaseSchedulerProvider {
    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}
