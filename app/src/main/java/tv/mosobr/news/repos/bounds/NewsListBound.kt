package tv.mosobr.news.repos.bounds

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import tv.mosobr.news.di.ApiResponse
import tv.mosobr.news.model.api.NewsApi
import tv.mosobr.news.model.data.NewsListResponse
import tv.mosobr.news.tools.AppExecutors

class NewsListBound(appExecutors: AppExecutors,
                    private val newsApi: NewsApi) : NetworkBound<NewsListResponse>(appExecutors) {

    private var response: NewsListResponse? = null

    private var dateEnd = ""

    fun setParams(dateEnd: String): NewsListBound {
        this.dateEnd = dateEnd
        return this
    }

    override fun shouldFetch(data: NewsListResponse?): Boolean {
        return data == null && response == null
    }

    override fun loadSaved(): LiveData<NewsListResponse> {
        val result = MutableLiveData<NewsListResponse>()
        result.postValue(response)
        return result
    }

    override fun saveResult(data: NewsListResponse?) {
        if (data == null) return
        response = data
    }

    override fun createCall(): LiveData<ApiResponse<NewsListResponse>> {
        return newsApi.getNewsList(LIMIT, dateEnd)
    }

    companion object {
        private const val LIMIT = 10
    }
}