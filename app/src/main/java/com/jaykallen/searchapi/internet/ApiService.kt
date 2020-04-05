package com.jaykallen.searchapi.internet


import com.jaykallen.searchapi.model.Teams
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/v1/json/1/search_all_teams.php")
    // https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League
    abstract fun queryAllTeams(@Query("l") name: String): Call<Teams>

    @GET("/api/v1/json/1/searchteams.php")
    // https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=B
    abstract fun querySpecificTeam(@Query("t") name: String): Call<Teams>

}