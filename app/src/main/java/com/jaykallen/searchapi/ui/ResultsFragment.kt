package com.jaykallen.searchapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jaykallen.searchapi.R
import com.jaykallen.searchapi.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_results.*

class ResultsFragment : Fragment() {
    private var teamChosen: Team? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println("***************** Results Fragment *******************")
        getSafeArgs()
    }

    private fun getSafeArgs() {
        arguments?.let {
            val args = ResultsFragmentArgs.fromBundle(it)
            teamChosen = args.choice
            println("Safe Argument Received=${teamChosen?.strTeam}")
            updateUi(teamChosen ?: Team())
        }
    }

    private fun updateUi(team: Team) {
        println("${team.strTeam} selected")
        teamText.text = team.strTeam
        descText.text = team.strDescriptionEN
        Picasso.get().load(team.strTeamBadge).into(badgeImage);
    }
}