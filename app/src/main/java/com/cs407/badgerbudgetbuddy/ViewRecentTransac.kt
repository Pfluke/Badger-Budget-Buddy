package com.cs407.badgerbudgetbuddy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ViewRecentTransac : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.view_recent_transac, container, false)

        val backBtn: Button = rootView.findViewById<Button>(R.id.back3)

        val navController = findNavController()

        backBtn.setOnClickListener {
            navController.navigate(R.id.action_viewRecentTransac_to_home)
        }

        val linearLayoutContainer = rootView.findViewById<LinearLayout>(R.id.linearLayoutContainer)

        // Example data
        val data = listOf(
            Pair("Card 1", "Placeholder."),
            Pair("Card 2", "Placeholder."),
            Pair("Card 3", "Placeholder."),
            Pair("Card 4", "Placeholder."),
            Pair("Card 5", "Placeholder."),
            Pair("Card 6", "Placeholder."),
            Pair("Card 7", "Placeholder."),
            Pair("Card 8", "Placeholder."),
            Pair("Card 9", "Placeholder."),
            Pair("Card 10", "Placeholder."),
            Pair("Card 11", "Placeholder."),
            Pair("Card 12", "Placeholder."),
            Pair("Card 13", "Placeholder."),
        )

        // Dynamically add cards
        for ((title, description) in data) {
            val cardView = inflater.inflate(R.layout.transaction_card, linearLayoutContainer, false)

            val cardTitle = cardView.findViewById<TextView>(R.id.cardTitle)
            val cardDescription = cardView.findViewById<TextView>(R.id.cardDescription)

            cardTitle.text = title
            cardDescription.text = description

            linearLayoutContainer.addView(cardView)
        }

        return rootView
    }
}
