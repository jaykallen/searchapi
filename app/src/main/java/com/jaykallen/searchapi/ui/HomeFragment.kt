package com.jaykallen.searchapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaykallen.searchapi.R
import com.jaykallen.searchapi.internet.RestManager
import com.jaykallen.searchapi.model.Team
import com.jaykallen.searchapi.model.TeamChosen
import com.jaykallen.searchapi.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

// This is a take home assignment given to me by Reachmobi on 03/27/2020
// track a person’s favorite sports team and look up their game history
// Implement a search functionality to allow users to query results from the API
// tracking and analytics to determine how your users are using your app. How would you approach this?
// Firebase and / or Flurry
// Monetization strategies?
// Provide ad network with the team name chosen
// Request age / gender of user (if personalized ads allowed)

class HomeFragment : Fragment() {
    private lateinit var recyclerAdapter: HomeAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println("***************** Home Fragment *******************")
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModel.observeLiveData(this)
        setSearchViewListener()
    }

    private fun setSearchViewListener() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isNullOrBlank()) {
                    getTeams(query)
                }
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun getTeams(input: String) {
        println("Get Teams Initiated")
        RestManager.getSpecificName(input)
        homeViewModel.teamsLiveData.observe(viewLifecycleOwner, Observer { teamsData ->
            println("Team Data received $teamsData")
            setupRecycler(teamsData)
        })
    }

    private fun setupRecycler(itemList: ArrayList<Team>) {
        recyclerAdapter = HomeAdapter(itemList) { chosen ->
            choiceClicked(chosen)
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = recyclerAdapter
    }

    private fun choiceClicked(chosen: Team) {
        println("User clicked: ${chosen.strTeam}")
        homeViewModel.choice = chosen       // Pass object through viewmodel??
        TeamChosen.teamChosen = chosen      // Pass object with data object (Ryan will hate this)
//        val action = HomeFragmentDirections.actionHomeFragmentToResultsFragment(chosen)
//        Navigation.findNavController(view!!).navigate(action)  // Pass object through safe args??
        Navigation.findNavController(view!!).navigate(R.id.action_homeFragment_to_resultsFragment)
    }

}
