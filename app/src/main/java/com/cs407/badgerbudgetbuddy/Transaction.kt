package com.cs407.badgerbudgetbuddy
// Contains the transactional information
data class Transaction(
    val date: String,
    val amount: Double,
    val description: String,
    val type: String
)
