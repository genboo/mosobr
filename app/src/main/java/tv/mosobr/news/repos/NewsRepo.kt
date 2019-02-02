package tv.mosobr.news.repos

import android.arch.lifecycle.LiveData
import tv.mosobr.news.model.api.NewsApi
import tv.mosobr.news.model.data.Post
import tv.mosobr.news.model.data.Resource
import tv.mosobr.news.model.data.NewsListResponse
import tv.mosobr.news.repos.bounds.NewsBound
import tv.mosobr.news.repos.bounds.NewsByCategoryListBound
import tv.mosobr.news.repos.bounds.NewsListBound
import tv.mosobr.news.tools.AppExecutors
import javax.inject.Inject

class NewsRepo @Inject
constructor(private val appExecutors: AppExecutors,
            private val newsApi: NewsApi) {


    fun getNewsList(dateEnd: String): LiveData<Resource<NewsListResponse>> {
        return NewsListBound(appExecutors, newsApi)
                .setParams(dateEnd)
                .create()
                .asLiveData()
    }

    fun getNewsByCategoryList(category: String, dateEnd: String): LiveData<Resource<NewsListResponse>> {
        return NewsByCategoryListBound(appExecutors, newsApi)
                .setParams(category, dateEnd)
                .create()
                .asLiveData()
    }

    fun getNews(id: Int): LiveData<Resource<Post>> {
        return NewsBound(appExecutors, newsApi)
                .setParams(id)
                .create()
                .asLiveData()
    }

}