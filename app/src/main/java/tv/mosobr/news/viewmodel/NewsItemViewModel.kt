package tv.mosobr.news.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import tv.mosobr.news.model.data.Post
import tv.mosobr.news.model.data.Resource
import tv.mosobr.news.repos.NewsRepo
import tv.mosobr.news.tools.AbsentLiveData

import javax.inject.Inject


/**
 * ViewModel для новости
 */

class NewsItemViewModel @Inject
internal constructor(newsRepo: NewsRepo) : ViewModel() {

    private val switch = MutableLiveData<Int>()
    val post: LiveData<Resource<Post>>

    init {
        post = Transformations.switchMap(switch) {
            if (it != null) {
                return@switchMap newsRepo.getNews(it)
            }
            return@switchMap AbsentLiveData.create<Resource<Post>>()
        }
    }

    /**
     * Загрузка новостей
     */
    fun loadNews(id: Int) {
        switch.postValue(id)
    }

}
