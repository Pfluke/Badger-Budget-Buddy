package com.cs407.badgerbudgetbuddy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewRecentTransac : Fragment() {

    // List of transactions to display
    private var transactionsList: List<Transaction> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.view_recent_transac, container, false)

        val backBtn: Button = rootView.findViewById<Button>(R.id.back3)
        val submitTransacBtn = rootView.findViewById<Button>(R.id.addTransacBtn)

        val navController = findNavController()

        backBtn.setOnClickListener {
            navController.navigate(R.id.action_viewRecentTransac_to_home)
        }

        submitTransacBtn.setOnClickListener {
            navController.navigate(R.id.action_viewRecentTransac_to_AddTransac)
        }

        val linearLayoutContainer = rootView.findViewById<LinearLayout>(R.id.linearLayoutContainer)
        val dao = BadgerDatabase.getDatabase(requireContext()).transactionDao()
        lifecycleScope.launch {
            val transactions = withContext(Dispatchers.IO) {
                val dao = BadgerDatabase.getDatabase(requireContext()).transactionDao()
                dao.getAllTransactions()
            }

            for (transaction in transactionsList) {
                val cardView = inflater.inflate(R.layout.transaction_card, linearLayoutContainer, false)

                val cardTitle = cardView.findViewById<TextView>(R.id.cardTitle)
                val cardDescription = cardView.findViewById<TextView>(R.id.cardDescription)

                cardTitle.text = transaction.type
                cardDescription.text = transaction.description

                linearLayoutContainer.addView(cardView)
            }
        }

//          val data =
//            listOf(
//            Pair("Card 1", "Placeholder."),
//            Pair("Card 2", "Placeholder."),
//            Pair("Card 3", "Placeholder."),
//            Pair("Card 4", "Placeholder."),
//            Pair("Card 5", "Placeholder."),
//            Pair("Card 6", "Placeholder."),
//            Pair("Card 7", "Placeholder."),
//            Pair("Card 8", "Placeholder."),
//            Pair("Card 9", "Placeholder."),
//            Pair("Card 10", "Placeholder."),
//            Pair("Card 11", "Placeholder."),
//            Pair("Card 12", "Placeholder."),
//            Pair("Card 13", "Placeholder."),
//        )



        return rootView
    }
}
