package com.example.convidados.service.repository

import androidx.room.*
import com.example.convidados.service.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun save(guest: GuestModel) : Long

    @Update
    fun update(guest: GuestModel) : Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM Guests WHERE id= :id")
    fun get(id: Int): GuestModel

    @Query("SELECT * FROM Guests")
    fun getInvited(): List<GuestModel>

    @Query("SELECT * FROM Guests WHERE presence= 1")
    fun getPresents(): List<GuestModel>

    @Query("SELECT * FROM Guests WHERE presence= 0")
    fun getAbsents(): List<GuestModel>
}