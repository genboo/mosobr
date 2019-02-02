package tv.mosobr.news.di.components

import android.content.Context

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import tv.mosobr.news.di.modules.NavigationModule
import tv.mosobr.news.di.modules.NewsRetrofitModule
import tv.mosobr.news.di.modules.ViewModelModule
import tv.mosobr.news.view.activities.MainActivity
import tv.mosobr.news.view.fragments.BaseFragment

/**
 * Основной компонент dagger 2 для инъекции зависимостей
 */
@Component(modules = [
    ViewModelModule::class,
    NewsRetrofitModule::class,
    NavigationModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }


    fun inject(fragment: BaseFragment)

    fun inject(activity: MainActivity)

}