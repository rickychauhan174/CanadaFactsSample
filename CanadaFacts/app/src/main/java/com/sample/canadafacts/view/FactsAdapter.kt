package com.sample.canadafacts.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.canadafacts.R
import com.sample.canadafacts.model.FactsResponseModel
import com.sample.canadafacts.util.ImageUtils
import kotlinx.android.synthetic.main.facts_item.view.*

class FactsAdapter(
    var movieList: ArrayList<FactsResponseModel.Row>,
    val context: Context
) : RecyclerView.Adapter<FactsAdapter.PopularViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = PopularViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.facts_item, parent, false)
    )

    override fun getItemCount() = movieList.size
    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(movieList[position], context)
    }

    inner class PopularViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle = view.tvTitle
        private val tvDate = view.tvDescription
        private val imgPopularMovie = view.imgFacts
        fun bind(factsModel: FactsResponseModel.Row, context: Context) {
            factsModel.title?.let { tvTitle.text = factsModel.title}
            factsModel.description?.let {  tvDate.text = factsModel.description}
            factsModel.imageHref?.let { ImageUtils.setFactsImage(
                factsModel.imageHref,
                imgPopularMovie,
                context
            )}
        }
    }
}
