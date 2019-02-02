package tv.mosobr.news.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import tv.mosobr.news.model.data.CategoriesResponse
import tv.mosobr.news.model.data.Resource
import tv.mosobr.news.repos.CategoriesRepo
import tv.mosobr.news.tools.AbsentLiveData

import javax.inject.Inject


/**
 * ViewModel для категорий
 */

class CategoriesViewModel @Inject
internal constructor(categoriesRepo: CategoriesRepo) : ViewModel() {

    private val switch = MutableLiveData<Boolean>()
    val categories: LiveData<Resource<CategoriesResponse>>

    init {
        categories = Transformations.switchMap(switch) {
            if (it != null) {
                return@switchMap categoriesRepo.getCategories()
            }
            return@switchMap AbsentLiveData.create<Resource<CategoriesResponse>>()
        }
    }

    /**
     * Загрузка новостей
     */
    fun loadCategories() {
        switch.postValue(true)
    }

}
