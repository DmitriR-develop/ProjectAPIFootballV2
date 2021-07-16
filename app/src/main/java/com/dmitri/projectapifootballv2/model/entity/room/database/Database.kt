package com.dmitri.projectapifootballv2.model.entity.room.database

import androidx.room.RoomDatabase
import com.dmitri.projectapifootballv2.model.entity.room.RoomLeagues
import com.dmitri.projectapifootballv2.model.entity.room.RoomTeams
import com.dmitri.projectapifootballv2.model.entity.room.dao.LeaguesDao
import com.dmitri.projectapifootballv2.model.entity.room.dao.TeamsDao

@androidx.room.Database(entities = [RoomLeagues::class, RoomTeams::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val leaguesDao: LeaguesDao
    abstract val teamsDao: TeamsDao

    companion object {
        const val DB_NAME = "database.db"
        private var instance: Database? = null
    }
}