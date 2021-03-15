package com.gujun.common.base.ui.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.gujun.common.base.ui.adapter.BaseRecyclerAdapter

/**
 *    author : gujun
 *    date   : 2021/3/12 16:47
 *    desc   :
 */
class BaseViewHolder<T>(
    itemView: View,
    private val onHolderCallback: BaseRecyclerAdapter.OnHolderCallback<T>
) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: T, position: Int) {
        onHolderCallback.onUpdateItem(itemView, item, position)
    }

}