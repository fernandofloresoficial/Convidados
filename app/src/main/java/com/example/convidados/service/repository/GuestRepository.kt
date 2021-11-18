package com.example.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.service.constants.DataBaseConstants
import com.example.convidados.service.model.GuestModel

class GuestRepository (context: Context) {

    // Acesso ao banco de dados
    private val mDataBase = GuestDataBase.getDatabase(context).guestDAO()


    fun get(id: Int): GuestModel {
        return mDataBase.get(id)
    }

    fun getAll(): List<GuestModel> {
        return mDataBase.getInvited()
    }

    fun getPresents(): List<GuestModel> {
        return mDataBase.getPresents()
    }

    fun getAbsents(): List<GuestModel> {
        return mDataBase.getAbsents()
    }

    fun save(guest: GuestModel): Boolean {
        return mDataBase.save(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return mDataBase.update(guest) > 0
    }

    fun delete(guest: GuestModel) {
        mDataBase.delete(guest)
    }
}