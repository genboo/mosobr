package tv.mosobr.news.model.api

import android.arch.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tv.mosobr.news.di.ApiResponse
import tv.mosobr.news.model.data.CategoriesResponse
import tv.mosobr.news.model.data.Post
import tv.mosobr.news.model.data.NewsListResponse

/**
 * API для получения новостей
 */
interface NewsApi {

    @GET("/api/v0/categories/")
    fun getCategories(): LiveData<ApiResponse<CategoriesResponse>>

    @GET("/api/v0/posts?order_by[published_at]=desc")
    fun getNewsList(@Query("page_limit") limit: Int, @Query("date_end") dateEnd: String): LiveData<ApiResponse<NewsListResponse>>

    @GET("/api/v0/posts?order_by[published_at]=desc")
    fun getNewsListByCategory(@Query("category") category: String, @Query("page_limit") limit: Int, @Query("date_end") dateEnd: String): LiveData<ApiResponse<NewsListResponse>>

    @GET("/api/v0/posts/{id}")
    fun getNews(@Path("id") id: Int): LiveData<ApiResponse<Post>>

}