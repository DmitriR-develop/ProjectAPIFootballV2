package com.dmitri.projectapifootballv2

import android.os.Bundle
import com.dmitri.projectapifootballv2.R.layout.activity_main
import com.dmitri.projectapifootballv2.abs.AbsActivity
import com.dmitri.projectapifootballv2.navigation.AndroidScreen
import com.dmitri.projectapifootballv2.navigation.IBackButtonListener
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity() : AbsActivity(activity_main) {

    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState ?: router.newRootScreen(AndroidScreen.LeaguesScreens())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is IBackButtonListener && it.backPressed()) {
                return
            }
        }
    }
}