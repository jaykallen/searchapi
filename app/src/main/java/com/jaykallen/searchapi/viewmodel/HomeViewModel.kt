package com.jaykallen.searchapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.jaykallen.searchapi.internet.RestManager
import com.jaykallen.searchapi.model.Team
import com.jaykallen.searchapi.model.Teams
import com.jaykallen.searchapi.ui.HomeFragment

class HomeViewModel : ViewModel() {
    var teamsLiveData = MutableLiveData<ArrayList<Team>>()
    var teams = Teams()
    var teamList = ArrayList<Team>()
    var choice = Team()

    fun observeLiveData(context: HomeFragment) {
        RestManager.mutableLiveData.observe(context, Observer { teamsData ->
            teams = teamsData ?: Teams()
            parseTeams(teams)
        })
    }

    private fun parseTeams(teams: Teams) {
        // I know there's a better way to do this directly, but I took some shortcuts
        if (teams.teams != null) {
            if (teams.teams.size > 0) {
                teamList.clear()
                for (i in 0 until teams.teams.size) {
                    teamList.add(teams.teams[i])
                    println(teamList[i].strTeam + " Added!")
                }
            }
        }
        teamsLiveData.value = teamList
    }

}