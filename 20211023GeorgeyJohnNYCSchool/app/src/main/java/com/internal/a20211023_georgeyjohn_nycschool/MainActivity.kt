package com.internal.a20211023_georgeyjohn_nycschool

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.internal.a20211023_georgeyjohn_nycschool.databinding.ActivityMainBinding
import com.internal.adapter.LocationImageClick
import com.internal.adapter.SchoolListAdapter
import com.internal.viewmodel.SchoolListViewModel
import java.util.*

class MainActivity : AppCompatActivity(), LocationImageClick {

    private val viewModel: SchoolListViewModel by lazy {
        ViewModelProvider(this).get(SchoolListViewModel::class.java)
    }

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private val adapter = SchoolListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.rvSchool.adapter = adapter

        /**
         * Observing the School List.
         * When School List update this method will observe and update the list
         */
        viewModel.schoolList.observe(this, {
            if (it?.isNotEmpty() == true) {
                adapter.submitList(it)
            }
        })
    }

    /**
     * This function will fire on click of Image Icon
     * This function will open the location in in-build google map application
     * args lat, lon - Location co-ordinates
     */
    override fun onLocationImageClicked(lat: String, lon: String) {
        val uri: String =
            java.lang.String.format(Locale.ENGLISH, "geo:%f,%f", lat.toFloat(), lon.toFloat())
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        this.startActivity(intent)
    }

    /**
     * This function will fire on Recycler view Item click
     * This function will open the school details page
     * args dbn - Unique school identifier to pass to detail page
     */
    override fun onItemClick(dbn: String) {
        val i = Intent(this@MainActivity, SchoolDetailsActivity::class.java)
        i.putExtra("dbn", dbn)
        startActivity(i)
    }

}