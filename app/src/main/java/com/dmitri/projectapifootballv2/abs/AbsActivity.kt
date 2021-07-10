package com.dmitri.projectapifootballv2.abs

import android.os.Bundle
import androidx.annotation.LayoutRes
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import moxy.MvpAppCompatActivity
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

abstract class AbsActivity(@LayoutRes contentLayoutId: Int) : MvpAppCompatActivity(contentLayoutId),
    HasAndroidInjector {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

}