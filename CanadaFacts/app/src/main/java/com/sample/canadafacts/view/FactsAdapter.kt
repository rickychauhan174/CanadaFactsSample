package com.sample.canadafacts.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.canadafacts.R
import com.sample.canadafacts.databinding.FactsItemBinding
import com.sample.canadafacts.model.FactsResponseModel
import com.sample.canadafacts.util.ImageUtils
import kotlinx.android.synthetic.main.facts_item.view.*

class FactsAdapter(
    var movieList: ArrayList<FactsResponseModel.Row>,
    val context: Context
) : RecyclerView.Adapter<FactsAdapter.FactsViewHolder>() {
    private lateinit var mBinding: FactsItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): FactsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        mBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.facts_item, parent, false)
        return FactsViewHolder(mBinding)
    }

    override fun getItemCount() = movieList.size
    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        holder.bind(movieList[position], context)
    }

    inner class FactsViewHolder(val binding: FactsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(factsModel: FactsResponseModel.Row, context: Context) {
            binding.tvTitle.text = factsModel.title ?: ""
            binding.tvDescription.text = factsModel.description?: ""
            ImageUtils.setFactsImage(
                factsModel.imageHref,
                binding.imgFacts,
                context
            )
        }
    }
}
