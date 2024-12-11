package com.cs407.badgerbudgetbuddy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SwitchAccount : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView =  inflater.inflate(R.layout.switch_account, container, false)

        val backBtn: Button = rootView.findViewById<Button>(R.id.back1)
        val but1: Button = rootView.findViewById<Button>(R.id.button)

        val navController = findNavController()

        backBtn.setOnClickListener {
            navController.navigate(R.id.action_switchAccount_to_home)
        }
        but1.setOnClickListener {
            navController.navigate(R.id.action_switchAccount_to_home)
        }
        return rootView
    }
}
