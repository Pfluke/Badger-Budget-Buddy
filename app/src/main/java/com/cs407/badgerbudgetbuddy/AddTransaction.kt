package com.cs407.badgerbudgetbuddy

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class AddTransaction : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView =  inflater.inflate(R.layout.add_transaction, container, false)

        val submitBtn: Button = rootView.findViewById<Button>(R.id.submit_transaction)
        val cancelBtn: Button = rootView.findViewById<Button>(R.id.cancel)
        val quantity: EditText = rootView.findViewById<EditText>(R.id.editTransacQty)
        val category: Spinner = rootView.findViewById<Spinner>(R.id.category)
        val account: Spinner = rootView.findViewById<Spinner>(R.id.account)
        val description: EditText = rootView.findViewById<EditText>(R.id.description)

        quantity.inputType = InputType.TYPE_CLASS_NUMBER


        val navController = findNavController()

        submitBtn.setOnClickListener {
            if (quantity.text.isBlank() || quantity.text.equals("Enter Quantity (\$)") || category.selectedItem == null || account.selectedItem == null || description.text.isEmpty()) {
                Toast.makeText(activity, "Please fill out all required information!", Toast.LENGTH_SHORT).show()
            } else {
                navController.navigate(R.id.action_addTransaction_to_recentTransac)
                Toast.makeText(activity, "Transaction successfully submitted!", Toast.LENGTH_SHORT).show()
            }
        }

        cancelBtn.setOnClickListener {
            navController.navigate(R.id.action_addTransaction_to_recentTransac)
            Toast.makeText(activity, "Transaction cancelled!", Toast.LENGTH_SHORT).show()
        }

        return rootView
    }
}
