package com.dmitri.projectapifootballv2.model.entity.room.dao

import androidx.room.*
import com.dmitri.projectapifootballv2.model.entity.room.RoomLeagues
import io.reactivex.rxjava3.core.Completable

@Dao
interface LeaguesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(continents: List<RoomLeagues>): Completable

    @Update
    fun update(continent: RoomLeagues)

    @Delete
    fun delete(continent: RoomLeagues)

    @Query("SELECT * FROM RoomLeagues")
    fun getAll(): List<RoomLeagues>

    @Query("SELECT * FROM RoomLeagues WHERE id= :id LIMIT 1")
    fun findByName(id: Int): RoomLeagues?
}