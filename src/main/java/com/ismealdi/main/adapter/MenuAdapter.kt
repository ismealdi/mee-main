package com.ismealdi.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.ismealdi.main.R
import com.ismealdi.main.databinding.ItemMenuBinding
import com.ismealdi.meepopup.schema.Menu
import com.ismealdi.meepopup.util.binding.DataBoundBindAdapter
import com.ismealdi.meepopup.util.binding.DataBoundViewHolder
import com.ismealdi.meepopup.util.common.dpToPx


class MenuAdapter(
    private val context: Context,
    private val itemClick: ((Int, Menu) -> Unit)? = null
)
    : DataBoundBindAdapter<Menu, ViewDataBinding>(
    diffCallback = object : DiffUtil.ItemCallback<Menu>() {
        override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean {
            return oldItem.name == newItem.name
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate<ItemMenuBinding>(LayoutInflater.from(parent.context), R.layout.item_menu, parent, false)
    }

    override fun bind(
        binding: ViewDataBinding,
        item: Menu,
        view: View,
        position: Int,
        holder: DataBoundViewHolder<ViewDataBinding>
    ) {

        binding as ItemMenuBinding

        with(binding) {
            menu = item
            ratingMenu.setRating(item.rating)

            root.setOnClickListener {
                itemClick?.invoke(position, item)
            }

            val displayMetrics = DisplayMetrics()
            val windowmanager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowmanager.defaultDisplay.getMetrics(displayMetrics)
            var deviceWidth = displayMetrics.widthPixels
            val params = itemMenuParent.layoutParams

            params.width = (deviceWidth - (45.dpToPx())) / 2

            itemMenuParent.layoutParams = params
        }

    }


}