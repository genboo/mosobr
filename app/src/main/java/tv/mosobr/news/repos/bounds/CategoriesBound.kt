package tv.mosobr.news.repos.bounds

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import tv.mosobr.news.di.ApiResponse
import tv.mosobr.news.model.api.NewsApi
import tv.mosobr.news.model.data.CategoriesResponse
import tv.mosobr.news.tools.AppExecutors

class CategoriesBound(appExecutors: AppExecutors,
                      private val newsApi: NewsApi) : NetworkBound<CategoriesResponse>(appExecutors) {

    private var response: CategoriesResponse? = null

    override fun shouldFetch(data: CategoriesResponse?): Boolean {
        return data == null && response == null
    }

    override fun loadSaved(): LiveData<CategoriesResponse> {
        val result = MutableLiveData<CategoriesResponse>()
        result.postValue(response)
        return result
    }

    override fun saveResult(data: CategoriesResponse?) {
        if (data == null) return
        response = data
    }

    override fun createCall(): LiveData<ApiResponse<CategoriesResponse>> {
        return newsApi.getCategories()
    }

}