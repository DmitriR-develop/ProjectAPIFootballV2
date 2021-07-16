package com.dmitri.projectapifootballv2.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomLeagues(
    @PrimaryKey var id: Int,
    var name: String,
    var country: String,
    var code: String,
    var season: Int,
    var team: Int,
    var type: String,
    var current: String,
    var search: String,
    var last: String
)
