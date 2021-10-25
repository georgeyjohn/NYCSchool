package com.internal.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.internal.model.School
import com.internal.repository.SchoolRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

/**
 * This is the ViewModel for the School List
 * getSchoolList - API call using coroutine
 * Using Flow instead as callback function
 */

class SchoolListViewModel : ViewModel() {
    val schoolList = MutableLiveData<List<School?>>()
    // val searchText = MutableLiveData<String>()
    var showError = MutableLiveData<String?>()
    var showLoading = MutableLiveData<Boolean?>()

    init {
        getSchoolList()
    }

    private fun getSchoolList() {
        CoroutineScope(Dispatchers.Main).launch {
            showLoading.value = true
            SchoolRepository().getSchoolList().collect {
                showLoading.value = false
                if (it?.schoolList?.isEmpty() == true) {
                    if (it.errorMessage?.length == 0) {
                        showError.value = "No Data to show"
                    } else {
                        showError.value = it.errorMessage
                    }
                    schoolList.value = arrayListOf()
                } else {
                    showError.value = ""
                    schoolList.value = it?.schoolList!!
                }
            }
        }
    }
}