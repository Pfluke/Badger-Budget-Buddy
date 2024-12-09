package com.cs407.badgerbudgetbuddy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ViewReceipts : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.view_receipts, container, false)

        val backBtn: Button = rootView.findViewById<Button>(R.id.back2)

        val navController = findNavController()

        backBtn.setOnClickListener {
            navController.navigate(R.id.action_viewReceipts_to_home)
        }
        return rootView
    }
}
