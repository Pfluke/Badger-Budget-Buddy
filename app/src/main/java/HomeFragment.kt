package com.cs407.badgerbudgetbuddy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val switchAccountButton: Button = view.findViewById(R.id.switchAcct)
        val viewReceiptsButton: Button = view.findViewById(R.id.viewReceipt)
        val viewRecentTransacButton: Button = view.findViewById(R.id.viewRecentTransac)

        switchAccountButton.setOnClickListener {
            findNavController().navigate(R.id.switchAccount)
        }

        viewReceiptsButton.setOnClickListener {
            findNavController().navigate(R.id.viewReceipt)
        }

        viewRecentTransacButton.setOnClickListener {
            findNavController().navigate(R.id.viewRecentTransac)
        }

        return view
    }
}
