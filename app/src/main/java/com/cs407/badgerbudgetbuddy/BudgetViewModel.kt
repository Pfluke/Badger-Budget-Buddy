package com.cs407.badgerbudgetbuddy

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BudgetViewModel (application: Application) : AndroidViewModel(application){
    private val database = BadgerDatabase.getDatabase(application)
    private val userDao = database.userDao()
    private val transactionDao = database.transactionDao()

    val transactions: LiveData<List<Transaction>> = transactionDao.getAllTransactions()
    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionDao.insertTransaction(transaction)
        }
    }

//    fun getTransactions(): LiveData<List<Transaction>> {
//        return transactionDao.getAllTransactions()
//    }


    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionDao.deleteTransaction(transaction)
        }
    }

    // mostly for test purposes
    fun deleteAllTransaction() {
        viewModelScope.launch(Dispatchers.IO) {
            transactionDao.deleteAllTransactions()
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insertUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.deleteUser(user)
        }
    }

    fun getTransactionTotals(): LiveData<List<Double>> {
        return transactionDao.getTransactionTotals()
    }

}