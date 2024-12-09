package com.cs407.badgerbudgetbuddy

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BudgetViewModel (application: Application) : AndroidViewModel(application){
    private val database = BadgerDatabase.getDatabase(application)
    private val userDao = database.userDao()
    private val transactionDao = database.transactionDao()

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            transactionDao.insertTransaction(transaction)
        }
    }

    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch {
            transactionDao.deleteTransaction(transaction)
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            userDao.insertUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userDao.deleteUser(user)
        }
    }
}