package com.lolkek.accu.screen.details.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lolkek.accu.R
import kotlinx.android.synthetic.main.forecast_item.view.*

class ForecastsRecyclerViewAdapter : RecyclerView.Adapter<ForecastsRecyclerViewAdapter.CustomViewHolder>() {

    private var items: List<ForecastViewModel> = emptyList()
    var callback: Callback? = null

    fun bindData(newItems: List<ForecastViewModel>) {
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
        CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.forecast_item, parent, false))


    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var itemId: String = ""

        init {
            itemView.setOnClickListener { callback?.onCliked(itemId) }
        }

        fun bind(city: ForecastViewModel) {
            itemView.apply {
                tvDate.text = city.date
                tvLow.text = resources.getString(R.string.low_temp, city.low)
                tvHigh.text = resources.getString(R.string.hi_temp, city.high)
            }

        }
    }

    interface Callback {
        fun onCliked(id: String)
    }
}