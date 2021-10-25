package com.internal.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.internal.model.SchoolDetails
import com.internal.repository.SchoolDetailsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * This is the ViewModel for the School Details
 * getSchoolDetailsList - API call using coroutine
 * Using Flow instead as callback function
 */

class SchoolDetailsViewModel : ViewModel() {
    var schoolDetailsList = MutableLiveData<SchoolDetails?>()
    var showError = MutableLiveData<String?>()
    var showLoading = MutableLiveData<Boolean?>()

    val getSchoolDetails: MutableLiveData<SchoolDetails?> get() = schoolDetailsList

    fun getSchoolDetailsList(dbn: String?) {
        CoroutineScope(Dispatchers.Main).launch {
            showLoading.value = true
            SchoolDetailsRepository().getSchoolDetailsList(dbn).collect {
                showLoading.value = false
                if (it?.schoolDetailsList?.isEmpty() == true) {
                    if (it.errorMessage?.length == 0) {
                        showError.value = "No Details Available"
                    } else {
                        showError.value = it.errorMessage
                    }
                    schoolDetailsList.value = null
                } else {
                    showError.value = ""
                    schoolDetailsList.value = it?.schoolDetailsList!![0]!!
                }
            }
        }
    }
}