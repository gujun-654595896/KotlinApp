package com.gujun.common.base.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gujun.common.base.ui.adapter.holder.DataBindingViewHolder

/**
 *    author : gujun
 *    date   : 2021/1/6 14:40
 *    desc   : databinding 关联Recycler的基类Adapter
 */
abstract class DataBindingAdapter<T>(
    diffCallback: DiffUtil.ItemCallback<T>,
    private var layoutId: Int = 0,
    private val itemBRId: Int = 0,
    private val clickListenerVariableId: Int = 0
) : ListAdapter<T, DataBindingViewHolder>(diffCallback) {

    private var l: OnItemViewClickListener<T>? = null

    fun setOnItemViewClickListener(listener: OnItemViewClickListener<T>) {
        l = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBindingViewHolder {
        layoutId = if (layoutId == 0) getLayoutId() else layoutId
        if (layoutId <= 0)
            throw IllegalArgumentException("add layoutId in DataBindingAdapter")
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
        val viewHolder = DataBindingViewHolder(binding.root)
        viewHolder.setBinding(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        val videoListViewHolder = holder as DataBindingViewHolder
        if (itemBRId > 0) videoListViewHolder.getBinding()!!
            .setVariable(itemBRId, getItem(position))
        if (l != null && clickListenerVariableId > 0) videoListViewHolder.getBinding()!!
            .setVariable(clickListenerVariableId, l)
        videoListViewHolder.getBinding()!!.executePendingBindings()
    }

    open fun getLayoutId(): Int = 0

    interface OnItemViewClickListener<T> {
        fun onViewClick(v: View?, program: T)
    }

}
