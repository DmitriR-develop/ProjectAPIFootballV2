package com.dmitri.projectapifootballv2

import com.dmitri.projectapifootballv2.modules.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import ru.terrakok.cicerone.Cicerone

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()
                withRouter(cicerone.router)
                withNavigatorHolder(cicerone.navigatorHolder)
            }
            .withScheduler(AndroidSchedulers.mainThread())
            .build()
}