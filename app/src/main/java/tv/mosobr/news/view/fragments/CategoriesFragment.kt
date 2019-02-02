package tv.mosobr.news.view.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.fragment_news.*
import tv.mosobr.news.R
import tv.mosobr.news.model.data.CategoriesResponse
import tv.mosobr.news.model.data.Resource
import tv.mosobr.news.model.data.Status
import tv.mosobr.news.view.components.adapter.CategoriesListAdapter
import tv.mosobr.news.viewmodel.CategoriesViewModel

/**
 * Список новостей
 */
class CategoriesFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        initFragment()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        updateToolbar(getString(R.string.screen_categories))

        val viewModel = getViewModel(this, CategoriesViewModel::class.java)
        viewModel.categories.observe(viewLifecycleOwner, Observer { observeNews(it) })

        if (viewModel.categories.value == null) {
            viewModel.loadCategories()
        }

        val adapter = CategoriesListAdapter()
        list.layoutManager = LinearLayoutManager(requireContext())
        list.adapter = adapter
        adapter.setOnItemClickListener { _, item, _ ->
            navigator.toNewsByCategory(item.slug, item.title)
        }
    }


    private fun observeNews(data: Resource<CategoriesResponse>?) {
        if (data == null) return
        when (data.status) {
            Status.SUCCESS -> {
                if (data.data?.categories == null) return
                (list.adapter as CategoriesListAdapter).setItems(data.data.categories)
            }
            else -> {
                //not use
            }
        }
    }
}
