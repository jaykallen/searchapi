package com.jaykallen.searchapi.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Teams (
    @SerializedName("teams") var teams: List<Team>? = null
) : Parcelable