package tv.mosobr.news.view.components.adapter

import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tv.mosobr.news.R
import tv.mosobr.news.model.data.Category
import tv.mosobr.news.view.components.adapter.diffs.CategoryDiffCallback
import tv.mosobr.news.view.components.adapter.holders.CategoryHolder

class CategoriesListAdapter  : RecyclerViewAdapter<Category, CategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val holder = CategoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_category, parent, false))
        holder.setListener(View.OnClickListener { v -> onItemClick(v, holder.adapterPosition) })
        return holder
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(getItem(holder.adapterPosition))
    }

    override fun setItems(items: List<Category>) {
        val diffs = DiffUtil.calculateDiff(CategoryDiffCallback(getItems(), items), !getItems().isEmpty())
        super.setItems(items)
        diffs.dispatchUpdatesTo(this)
    }

}