package com.example.convidados.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.databinding.FragmentAllBinding
import com.example.convidados.databinding.FragmentPresentBinding
import com.example.convidados.service.constants.GuestConstants
import com.example.convidados.view.adapter.GuestAdapter
import com.example.convidados.view.listener.GuestListener
import com.example.convidados.viewmodel.GuestsViewModel

class PresentsFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null
    private lateinit var mViewModel: GuestsViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPresentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //-> ViewModel
        mViewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)

        //-> RecyclerView
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_presents)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        //-> Listener
        mListener = object : GuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)

                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.PRESENTS)
            }
        }

        //-> Adapter
        mAdapter.attachListener(mListener)

        observer()

        return root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observer() {
        mViewModel.guestlist.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
            mAdapter.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(GuestConstants.FILTER.PRESENTS)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}