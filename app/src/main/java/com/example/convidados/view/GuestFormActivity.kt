package com.example.convidados.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.viewmodel.GuestFormViewModel
import com.example.convidados.R
import com.example.convidados.service.constants.GuestConstants
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: GuestFormViewModel
    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)
        button_Save.setOnClickListener(this)

        setListeners()
        observe()
        loadData()

        radio_Present.isChecked = true

    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_Save) {

            val name = edit_Name.text.toString()
            val presence = radio_Present.isChecked
            mViewModel.save(mGuestId, name, presence)

        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mGuestId = bundle.getInt(GuestConstants.GUESTID)
            mViewModel.load(mGuestId)
        }
    }

    private fun observe() {

        mViewModel.saveGuest.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucessso", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_LONG).show()
            }
            finish()
        })

        mViewModel.guest.observe(this, {
            edit_Name.setText(it.name)
            if (it.presence) {
                radio_Present.isChecked = true
            } else {
                radio_Absent.isChecked = true
            }
        })
    }

    private fun setListeners() {
    }


}