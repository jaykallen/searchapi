package com.jaykallen.searchapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jaykallen.searchapi.internet.RestManager
import com.jaykallen.searchapi.model.Team

class HomeViewModel : ViewModel() {

    // Ryan's suggestion on pass through to the RestManager from the Home Fragment
    val teamsLiveData: LiveData<ArrayList<Team>>
        get() = RestManager.mutableLiveData

}