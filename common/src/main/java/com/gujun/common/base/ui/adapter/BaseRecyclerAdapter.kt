package com.gujun.common.base.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gujun.common.base.ui.adapter.holder.BaseViewHolder

/**
 *    author : gujun
 *    date   : 2021/1/6 14:39
 *    desc   : RecyclerView的Adapter基类
 */
abstract class BaseRecyclerAdapter<T>(
    private var layoutId: Int = 0,
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseViewHolder<T>>(diffCallback) {

    private var l: OnItemViewClickListener<T>? = null

    fun setOnItemViewClickListener(listener: OnItemViewClickListener<T>) {
        l = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        layoutId = if (layoutId == 0) getLayoutId() else layoutId
        if (layoutId <= 0)
            throw IllegalArgumentException("add layoutId in BaseRecyclerAdapter")
        return BaseViewHolder(LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false),
            object : OnHolderCallback<T> {
                override fun onUpdateItem(itemView: View, item: T, position: Int) {
                    onUpdateItemData(itemView, item, position)
                }
            })
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(getItem(position), position)
    }

    abstract fun onUpdateItemData(itemView: View, item: T, position: Int)

    open fun getLayoutId(): Int = 0

    interface OnItemViewClickListener<T> {
        fun onViewClick(v: View?, program: T)
    }

    interface OnHolderCallback<T> {
        fun onUpdateItem(itemView: View, item: T, position: Int)
    }

}