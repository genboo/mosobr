package tv.mosobr.news.view.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.*
import kotlinx.android.synthetic.main.fragment_news_item.*
import tv.mosobr.news.R
import tv.mosobr.news.model.data.Post
import tv.mosobr.news.model.data.Resource
import tv.mosobr.news.model.data.Status
import tv.mosobr.news.tools.format
import tv.mosobr.news.tools.loadImage
import tv.mosobr.news.viewmodel.NewsItemViewModel

/**
 * Новость
 */
class NewsItemFragment : BaseFragment() {

    private var newsId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_news_item, container, false)
        initFragment()

        newsId = args.getInt(ARG_ID)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val viewModel = getViewModel(this, NewsItemViewModel::class.java)
        viewModel.post.observe(viewLifecycleOwner, Observer { observeNews(it) })

        if (viewModel.post.value == null) {
            viewModel.loadNews(newsId)
        }


    }


    private fun observeNews(data: Resource<Post>?) {
        if (data == null) return
        when (data.status) {
            Status.SUCCESS -> {
                if (data.data == null) return
                fillContent(data.data)
            }
            else -> {
                //not use
            }
        }
    }

    private fun fillContent(item: Post) {
        updateToolbar(item.title)
        title.text = item.title
        content.text = item.content
        date.text = item.publishedDate.format(Post.FORMATTED_PATTERN)
        image.loadImage(item.imageUrl)
    }

    companion object {
        private const val ARG_ID = "id"

        fun getInstance(id: Int): NewsItemFragment {
            val args = Bundle()
            args.putInt(ARG_ID, id)
            val fragment = NewsItemFragment()
            fragment.arguments = args
            return fragment
        }

    }
}
