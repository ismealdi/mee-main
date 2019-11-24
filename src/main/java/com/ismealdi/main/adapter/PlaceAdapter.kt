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
import com.ismealdi.main.databinding.ItemPlaceBinding
import com.ismealdi.meepopup.schema.Menu
import com.ismealdi.meepopup.schema.Place
import com.ismealdi.meepopup.util.binding.DataBoundBindAdapter
import com.ismealdi.meepopup.util.binding.DataBoundViewHolder
import com.ismealdi.meepopup.util.common.dpToPx


class PlaceAdapter(
    private val context: Context,
    private val itemClick: ((Int, Place) -> Unit)? = null
)
    : DataBoundBindAdapter<Place, ViewDataBinding>(
    diffCallback = object : DiffUtil.ItemCallback<Place>() {
        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.name == newItem.name
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate<ItemPlaceBinding>(LayoutInflater.from(parent.context), R.layout.item_place, parent, false)
    }

    override fun bind(
        binding: ViewDataBinding,
        item: Place,
        view: View,
        position: Int,
        holder: DataBoundViewHolder<ViewDataBinding>
    ) {

        binding as ItemPlaceBinding

        with(binding) {
            place = item

            root.setOnClickListener {
                itemClick?.invoke(position, item)
            }
        }

    }


}