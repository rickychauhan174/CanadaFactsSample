package com.sample.canadafacts.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        geFacts()
    }

    private fun geFacts() {
        mBinding.progressBar.visibility = View.VISIBLE
        val viewModel = ViewModelProvider(this).get(FactsViewModel::class.java)
        //ser db obj
        viewModel.getFacts()
            .observe(this, Observer { response ->
                mBinding.progressBar.visibility = View.GONE
                if (response != null && response.rows != null && response.rows!!.size > 0) {
                    supportActionBar!!.title = response.title
                    val factsAdapter =
                        FactsAdapter(
                            response.rows!!,
                            this@MainActivity
                        )
                    mBinding.rvFacts.adapter = factsAdapter
                }
            })
    }
}
