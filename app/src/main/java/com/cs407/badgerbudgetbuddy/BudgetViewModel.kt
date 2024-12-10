package com.cs407.badgerbudgetbuddy

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BudgetViewModel (application: Application) : AndroidViewModel(application){
    private val database = BadgerDatabase.getDatabase(application)
    private val userDao = database.userDao()
    private val transactionDao = database.transactionDao()

    fun addTransaction(transaction: Transaction) {
        transactionDao.insertTransaction(transaction)
    }

    fun getTransactions(): LiveData<List<Transaction>> {
        return transactionDao.getAllTransactions()
    }

    fun deleteTransaction(transaction: Transaction) {
        transactionDao.deleteTransaction(transaction)
    }

    fun addUser(user: User) {
        userDao.insertUser(user)
    }

    fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
}