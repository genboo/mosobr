package tv.mosobr.news.repos

import android.arch.lifecycle.LiveData
import tv.mosobr.news.model.api.NewsApi
import tv.mosobr.news.model.data.CategoriesResponse
import tv.mosobr.news.model.data.Resource
import tv.mosobr.news.repos.bounds.CategoriesBound
import tv.mosobr.news.tools.AppExecutors
import javax.inject.Inject

class CategoriesRepo @Inject
constructor(private val appExecutors: AppExecutors,
            private val newsApi: NewsApi) {


    fun getCategories(): LiveData<Resource<CategoriesResponse>> {
        return CategoriesBound(appExecutors, newsApi)
                .create()
                .asLiveData()
    }

}