package com.dmitri.projectapifootballv2.model.entity.room.dao

import androidx.room.*
import com.dmitri.projectapifootballv2.model.entity.room.RoomTeams
import io.reactivex.rxjava3.core.Completable

@Dao
interface TeamsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(continents: List<RoomTeams>): Completable

    @Update
    fun update(continent: RoomTeams)

    @Delete
    fun delete(continent: RoomTeams)

    @Query("SELECT * FROM RoomTeams WHERE id = :id ")
    fun findForLeagues(id: Int): List<RoomTeams>
}