package tv.mosobr.news.view.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.fragment_news.*
import tv.mosobr.news.R
import tv.mosobr.news.model.data.Resource
import tv.mosobr.news.model.data.Status
import tv.mosobr.news.model.data.NewsListResponse
import tv.mosobr.news.view.components.adapter.NewsListAdapter
import tv.mosobr.news.viewmodel.NewsByCategoryViewModel

/**
 * Список новостей в категории
 */
class CategoryNewsFragment : BaseFragment() {

    private lateinit var category: String
    private lateinit var categoryTitle: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_news, container, false)
        initFragment()

        category = args.getString(ARG_CATEGORY) ?: ""
        categoryTitle = args.getString(ARG_TITLE) ?: ""

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        updateToolbar(categoryTitle)

        val viewModel = getViewModel(this, NewsByCategoryViewModel::class.java)
        viewModel.news.observe(viewLifecycleOwner, Observer { observeNews(it) })

        if (viewModel.news.value == null) {
            viewModel.loadNews(category, "2019-02-01")
        }

        val adapter = NewsListAdapter()
        list.layoutManager = LinearLayoutManager(requireContext())
        list.adapter = adapter
        adapter.setOnItemClickListener { _, item, _ -> navigator.toNewsItem(item.id) }

    }


    private fun observeNews(data: Resource<NewsListResponse>?) {
        if (data == null) return
        when (data.status) {
            Status.SUCCESS -> {
                if (data.data?.posts == null) return
                (list.adapter as NewsListAdapter).setItems(data.data.posts)
            }
            else -> {
                //not use
            }
        }
    }


    companion object {
        private const val ARG_CATEGORY = "category"
        private const val ARG_TITLE = "title"

        fun getInstance(category: String, title: String): CategoryNewsFragment {
            val args = Bundle()
            args.putString(ARG_CATEGORY, category)
            args.putString(ARG_TITLE, title)
            val fragment = CategoryNewsFragment()
            fragment.arguments = args
            return fragment
        }

    }
}
