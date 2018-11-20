package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.views


interface ObservableMviView<ListenerType> : MviView {

    fun registerListener(listener: ListenerType)

    fun unregisterListener(listener: ListenerType)
}
