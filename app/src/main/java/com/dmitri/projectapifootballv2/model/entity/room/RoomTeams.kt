package com.dmitri.projectapifootballv2.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = RoomLeagues::class,
            parentColumns = ["id"],
            childColumns = ["name"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RoomTeams(
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
