package com.lolkek.weather.features.entry.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lolkek.weather.R
import kotlinx.android.synthetic.main.city_item.view.*

class CitiesRecyclerViewAdapter : RecyclerView.Adapter<CitiesRecyclerViewAdapter.CustomViewHolder>() {

    private var items: List<CityUiModel> = emptyList()
    var callback: Callback? = null

    fun bindData(newItems: List<CityUiModel>) {
        val oldItems = items

        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = oldItems.size
            override fun getNewListSize(): Int = newItems.size
            override fun areItemsTheSame(oldPosition: Int, newPosition: Int): Boolean =
                oldItems[oldPosition] == newItems[newPosition]

            override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean =
                oldItems[oldPosition] == newItems[newPosition]
        }
        this.items = newItems
        DiffUtil.calculateDiff(diffCallback).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false))


    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var itemId: String = ""

        init {
            itemView.setOnClickListener { callback?.onClicked(itemId) }
        }

        fun bind(city: CityUiModel) {
            itemView.tvName.text = city.name
            itemId = city.id
        }
    }

    interface Callback {
        fun onClicked(id: String)
    }
}