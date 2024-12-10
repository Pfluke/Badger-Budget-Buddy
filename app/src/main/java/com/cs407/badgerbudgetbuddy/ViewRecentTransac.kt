package com.cs407.badgerbudgetbuddy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.lifecycle.Observer
class ViewRecentTransac : Fragment() {

    private lateinit var transactionViewModel: BudgetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.view_recent_transac, container, false)

        // Get the buttons and set click listeners for navigation
        val backBtn: Button = rootView.findViewById(R.id.back3)
        val submitTransacBtn: Button = rootView.findViewById(R.id.addTransacBtn)
        val navController = findNavController()

        backBtn.setOnClickListener {
            navController.navigate(R.id.action_viewRecentTransac_to_home)
        }

        submitTransacBtn.setOnClickListener {
            navController.navigate(R.id.action_viewRecentTransac_to_AddTransac)
        }

        // Initialize the container that will hold the transaction views
        val linearLayoutContainer: LinearLayout = rootView.findViewById(R.id.linearLayoutContainer)

        // Initialize the ViewModel
        transactionViewModel = ViewModelProvider(this).get(BudgetViewModel::class.java)

        // Observe LiveData from the ViewModel using a lambda for conciseness
        transactionViewModel.transactions.observe(viewLifecycleOwner) { transactions ->
            // Clear the existing views to avoid stacking old transaction views
            linearLayoutContainer.removeAllViews()

            // Handle empty list
            if (transactions.isEmpty()) {
                val noTransactionsText = TextView(context).apply {
                    text = "No Transactions Available"
                    textSize = 18f
                }
                linearLayoutContainer.addView(noTransactionsText)
            } else {
                for (transaction in transactions) {
                    val cardView = inflater.inflate(R.layout.transaction_card, linearLayoutContainer, false)

                    val cardTitle: TextView = cardView.findViewById(R.id.cardTitle)
                    val cardDescription: TextView = cardView.findViewById(R.id.cardDescription)

                    cardTitle.text = transaction.type
                    cardDescription.text = transaction.description

                    linearLayoutContainer.addView(cardView)
                }
            }
        }

        return rootView
    }
}
//class ViewRecentTransac : Fragment() {
//
//    // List of transactions to display
//    //private var transactionsList: List<Transaction> = mutableListOf()
//    private lateinit var transactionViewModel: BudgetViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val rootView = inflater.inflate(R.layout.view_recent_transac, container, false)
//
//        val backBtn: Button = rootView.findViewById<Button>(R.id.back3)
//        val submitTransacBtn = rootView.findViewById<Button>(R.id.addTransacBtn)
//
//        val navController = findNavController()
//
//        backBtn.setOnClickListener {
//            navController.navigate(R.id.action_viewRecentTransac_to_home)
//        }
//
//        submitTransacBtn.setOnClickListener {
//            navController.navigate(R.id.action_viewRecentTransac_to_AddTransac)
//        }
//
//        val linearLayoutContainer = rootView.findViewById<LinearLayout>(R.id.linearLayoutContainer)
//
//        transactionViewModel = ViewModelProvider(this).get(BudgetViewModel::class.java)
//
//        // Observe LiveData from the ViewModel
//        transactionViewModel.transactions.observe(viewLifecycleOwner, Observer { transactions ->
//            // Clear existing views
//            linearLayoutContainer.removeAllViews()
//
//            // Add new views based on the updated list of transactions
//            for (transaction in transactions) {
//                val cardView =
//                    inflater.inflate(R.layout.transaction_card, linearLayoutContainer, false)
//
//                val cardTitle = cardView.findViewById<TextView>(R.id.cardTitle)
//                val cardDescription = cardView.findViewById<TextView>(R.id.cardDescription)
//
//                cardTitle.text = transaction.type
//                cardDescription.text = transaction.description
//
//                linearLayoutContainer.addView(cardView)
//            }
//        })
//
//        return rootView
//    }
//}

//        transactionViewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)
//        val dao = BadgerDatabase.getDatabase(requireContext()).transactionDao()
//        val transactions = dao.getAllTransactions()
//
//        for (transaction in transactionsList) {
//            val cardView =
//                inflater.inflate(R.layout.transaction_card, linearLayoutContainer, false)
//
//            val cardTitle = cardView.findViewById<TextView>(R.id.cardTitle)
//            val cardDescription = cardView.findViewById<TextView>(R.id.cardDescription)
//
//            cardTitle.text = transaction.type
//            cardDescription.text = transaction.description
//
//            linearLayoutContainer.addView(cardView)
//        }
//
//        return rootView
//    }
//}

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