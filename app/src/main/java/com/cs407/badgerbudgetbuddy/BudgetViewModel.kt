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

    suspend fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            transactionDao.insertTransaction(transaction)
        }
    }

    suspend fun getTransactions(): List<androidx.room.Transaction> {
        return transactionDao.getAllTransactions()
    }

    suspend fun deleteTransaction(transaction: Transaction) {
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