package tv.mosobr.news.repos.bounds

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import tv.mosobr.news.di.ApiResponse
import tv.mosobr.news.model.api.NewsApi
import tv.mosobr.news.model.data.Post
import tv.mosobr.news.tools.AppExecutors

class NewsBound(appExecutors: AppExecutors,
                private val newsApi: NewsApi) : NetworkBound<Post>(appExecutors) {

    private var response: Post? = null

    private var id = 0

    fun setParams(id: Int): NewsBound {
        this.id = id
        return this
    }

    override fun shouldFetch(data: Post?): Boolean {
        return data == null && response == null
    }

    override fun loadSaved(): LiveData<Post> {
        val result = MutableLiveData<Post>()
        result.postValue(response)
        return result
    }

    override fun saveResult(data: Post?) {
        if (data == null) return
        response = data
    }

    override fun createCall(): LiveData<ApiResponse<Post>> {
        return newsApi.getNews(id)
    }

}