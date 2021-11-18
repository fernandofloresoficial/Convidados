package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.service.constants.GuestConstants
import com.example.convidados.service.model.GuestModel
import com.example.convidados.service.repository.GuestRepository

class GuestsViewModel (application: Application) : AndroidViewModel(application) {

    private val mGuestRepository = GuestRepository(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestlist: LiveData<List<GuestModel>> = mGuestList

    fun load (filter: Int){
        if (filter == GuestConstants.FILTER.EMPTY){
            mGuestList.value =  mGuestRepository.getAll()
        }else if (filter == GuestConstants.FILTER.PRESENTS){
            mGuestList.value =  mGuestRepository.getPresents()
        }else{
            mGuestList.value =  mGuestRepository.getAbsents()
        }
    }

    fun delete (id: Int){
        val guest = mGuestRepository.get(id)
        mGuestRepository.delete(guest)
    }
}