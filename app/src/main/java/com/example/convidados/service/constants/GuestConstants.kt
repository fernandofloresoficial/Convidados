package com.example.convidados.service.constants

class GuestConstants private constructor() {
    companion object{
        const val GUESTID = "guestID"
    }

    object FILTER {
        const val EMPTY = 0
        const val PRESENTS = 1
        const val ABSENTS = 2
    }
}