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
import com.jaykallen.searchapi.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

// todo Ryan's Suggestion: Add unit tests

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
//        homeViewModel.observeLiveData(this)
        setSearchViewListener()
        observeData()
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
                if (!newText.isNullOrBlank()) {
                    getTeams(newText)
                }
                return false
            }
        })
    }

    private fun observeData() {
        homeViewModel.teamsLiveData.observe(viewLifecycleOwner, Observer { teamsData ->
            println("Team Data received $teamsData")
            setupRecycler(teamsData)
        })
    }


    private fun getTeams(input: String) {
        println("Get Teams Initiated")
        RestManager.getSpecificName(input)  // todo through the viewmodel
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
        val action = HomeFragmentDirections.actionHomeFragmentToResultsFragment(chosen)
        view?.let {
            Navigation.findNavController(it).navigate(action)
        }
        // go to results fragement without safe args:
//        Navigation.findNavController(view!!).navigate(R.id.action_homeFragment_to_resultsFragment)
    }

}
