package tv.mosobr.news

import android.app.Application
import tv.mosobr.news.di.components.AppComponent
import tv.mosobr.news.di.components.DaggerAppComponent

/**
 * Основной класс приложения
 */
class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().context(this).build()
    }

}