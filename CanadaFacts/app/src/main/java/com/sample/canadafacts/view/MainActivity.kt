package com.sample.canadafacts.view

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sample.canadafacts.R
import com.sample.canadafacts.databinding.ActivityMainBinding
import com.sample.canadafacts.viewmodel.FactsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        swipreRefresh()
        geFacts()
    }

    private fun swipreRefresh() {
        mBinding.swipeRefresh.setOnRefreshListener {
            mBinding.swipeRefresh.isRefreshing = false
            geFacts()
        }
    }

    private fun geFacts() {
        mBinding.progressBar.visibility = View.VISIBLE
        val viewModel = ViewModelProvider(this).get(FactsViewModel::class.java)
        viewModel.getFacts()
            .observe(this, Observer { response ->
                mBinding.progressBar.visibility = View.GONE
                if (response?.rows != null && response.rows!!.size > 0) {
                    val factList = response.rows
                    // Remove item if all parameters are empty or null using iterator to avoid concurrentModificationException
                    val iterator = factList!!.iterator()
                    while (iterator.hasNext()) {
                        val item = iterator.next()
                        if (TextUtils.isEmpty(item.title) && TextUtils.isEmpty(item.description) && TextUtils.isEmpty(
                                item.imageHref
                            )
                        ) {
                            iterator.remove()
                        }
                    }
                    supportActionBar!!.title = response.title
                    val factsAdapter =
                        FactsAdapter(
                            factList,
                            this@MainActivity
                        )
                    mBinding.rvFacts.adapter = factsAdapter
                }
            })
    }
}
