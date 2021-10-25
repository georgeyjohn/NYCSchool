package com.internal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.internal.a20211023_georgeyjohn_nycschool.databinding.ItemSchoolDetailsBinding
import com.internal.model.School

/**
 * Adapter function for the RecyclerView to show School list
 */

class SchoolListAdapter(private val mLocationImageListener: LocationImageClick) :
    ListAdapter<School, SchoolListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSchoolDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.itemSchool = getItem(position)
        holder.binding.locationImageClick = mLocationImageListener
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<School>() {
            override fun areItemsTheSame(oldItem: School, newItem: School): Boolean {
                return oldItem.dbn == newItem.dbn
            }

            override fun areContentsTheSame(oldItem: School, newItem: School): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(val binding: ItemSchoolDetailsBinding) : RecyclerView.ViewHolder(binding.root)

}

/**
 * Interface for the Recycler view Item click
 * onLocationImageClicked - To identify and pass location details on Location ImageView click (This will redirect to Google Map App)
 * onItemClick - To identify the Recycler view Item click(This will redirect to School detail page with dbn)
 */

interface LocationImageClick {
    fun onLocationImageClicked(lat: String, lon: String)
    fun onItemClick(dbn: String)
}