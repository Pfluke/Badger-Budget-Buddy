package com.cs407.badgerbudgetbuddy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cs407.badgerbudgetbuddy.R

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val switchAccountButton: Button = view.findViewById(R.id.switchAcct)
        val viewReceiptsButton: Button = view.findViewById(R.id.viewReceipt)
        val viewRecentTransacButton: Button = view.findViewById(R.id.viewRecentTransac)

        val navController = findNavController()
//making a change
        switchAccountButton.setOnClickListener {
            navController.navigate(R.id.action_home_to_switchAccount)
        }

        viewReceiptsButton.setOnClickListener {
            navController.navigate(R.id.action_home_to_viewReceipts)
        }

        viewRecentTransacButton.setOnClickListener {
            navController.navigate(R.id.action_home_to_viewRecentTransactions)
        }

        return view
    }
}


