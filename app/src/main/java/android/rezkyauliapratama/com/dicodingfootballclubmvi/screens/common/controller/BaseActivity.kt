package android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.controller

import android.os.Bundle
import android.rezkyauliapratama.com.dicodingfootballclubmvi.BaseApplication
import android.rezkyauliapratama.com.dicodingfootballclubmvi.di.screen.ActivityModule
import android.rezkyauliapratama.com.dicodingfootballclubmvi.di.screen.DaggerScreenComponent
import android.rezkyauliapratama.com.dicodingfootballclubmvi.di.screen.ScreenComponent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import org.jetbrains.anko.AnkoLogger
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.ViewMvvmFactory
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviIntent
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.mvi.MviState
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.screennavigator.ScreensNavigator
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.ViewModelFactoryImpl
import android.rezkyauliapratama.com.dicodingfootballclubmvi.screens.common.views.MviView
import androidx.databinding.ViewDataBinding
import javax.inject.Inject


abstract class BaseActivity<T : ViewModel,I : MviIntent, in S : MviState, U : MviView, V : ViewDataBinding>  : AppCompatActivity(), BaseMvi<I, S>, AnkoLogger{

    @Inject
    lateinit var viewMvvmFactory: ViewMvvmFactory

    @Inject
    lateinit var screensNavigator: ScreensNavigator

    @Inject
    lateinit var viewModelFactoryImpl: ViewModelFactoryImpl

    lateinit var mViewModel: T

    lateinit var mViewMvc: U

    lateinit var mDataBinding: V

    abstract fun inject()
    abstract fun initView()
    abstract fun initDataBinding()
    abstract fun registerListener()
    abstract fun unregisterListener()
    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun initViewModel(): T


    private var screenComponent: ScreenComponent?= null

    //function untuk init activity component
    fun initControllerComponent(): ScreenComponent? {
        if (screenComponent == null) {
            screenComponent = DaggerScreenComponent.builder()
                    .applicationComponent(BaseApplication.component)
                    .activityModule(ActivityModule(this))
                    .build()
        }
        return screenComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        this.mViewModel =  initViewModel()
        initView()
        initDataBinding()
        setContentView(mViewMvc.dataBinding.root)

    }

    override fun onStart() {
        super.onStart()
        registerListener()
        bind()
    }

    override fun onStop() {
        super.onStop()
        unregisterListener()
    }


}

