package com.internal.a20211023_georgeyjohn_nycschool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.internal.a20211023_georgeyjohn_nycschool.databinding.ActivitySchoolDetailsBinding
import com.internal.viewmodel.SchoolDetailsViewModel

class SchoolDetailsActivity : AppCompatActivity() {

    private val viewModel: SchoolDetailsViewModel by lazy {
        ViewModelProvider(this).get(SchoolDetailsViewModel::class.java)
    }

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivitySchoolDetailsBinding>(
            this,
            R.layout.activity_school_details
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.itemSchoolDetails = viewModel
        binding.lifecycleOwner = this

        /**
         * Receiving dbn and executing getSchoolDetailsList
         * getSchoolDetailsList() - function to get school details(API call)
         * dbn - Unique school identifier to get school details
         */

        val extras = intent.extras
        if (extras != null) {
            val dbn = extras.getString("dbn")
            viewModel.getSchoolDetailsList(dbn)
        }
    }
}