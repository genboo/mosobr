package tv.mosobr.news.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import tv.mosobr.news.model.data.Resource
import tv.mosobr.news.model.data.NewsListResponse
import tv.mosobr.news.repos.NewsRepo
import tv.mosobr.news.tools.AbsentLiveData

import javax.inject.Inject


/**
 * ViewModel для списка новостей
 */

class NewsViewModel @Inject
internal constructor(newsRepo: NewsRepo) : ViewModel() {

    private val switch = MutableLiveData<String>()
    val news: LiveData<Resource<NewsListResponse>>

    init {
        news = Transformations.switchMap(switch) {
            if (it != null) {
                return@switchMap newsRepo.getNewsList(it)
            }
            return@switchMap AbsentLiveData.create<Resource<NewsListResponse>>()
        }
    }

    /**
     * Загрузка новостей
     */
    fun loadNews(dateEnd: String) {
        switch.postValue(dateEnd)
    }

}
