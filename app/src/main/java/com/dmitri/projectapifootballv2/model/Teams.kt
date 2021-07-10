package com.dmitri.projectapifootballv2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Teams(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("code") val code: String,
    @SerializedName("season") val season: Int,
    @SerializedName("team") val team: Int,
    @SerializedName("type") val type: String,
    @SerializedName("current") val current: String,
    @SerializedName("search") val search: String,
    @SerializedName("last") val last: String
) : Parcelable