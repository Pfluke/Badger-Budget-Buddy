package com.cs407.badgerbudgetbuddy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
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
                    //Log.d("DatabaseLog", transactionViewModel.getTransactionTotals().toString())
                    val cardTitle: TextView = cardView.findViewById(R.id.title)
                    val cardDescription: TextView = cardView.findViewById(R.id.description)

                    cardTitle.text = transaction.type
                    cardDescription.text = transaction.description

                    linearLayoutContainer.addView(cardView)
                }
            }
        }

        return rootView
    }
}