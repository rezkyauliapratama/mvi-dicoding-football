package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.views

import org.jetbrains.anko.AnkoLogger
import java.util.*

abstract class BaseObservableMviView<ListenerType> : BaseMviView(), ObservableMviView<ListenerType>, AnkoLogger {

    private val mListeners = HashSet<ListenerType>()

    protected val listeners: Set<ListenerType>
        get() = Collections.unmodifiableSet(mListeners)


    override fun registerListener(listener: ListenerType) {
        mListeners.add(listener)
    }

    override fun unregisterListener(listener: ListenerType) {
        mListeners.remove(listener)
    }
}
