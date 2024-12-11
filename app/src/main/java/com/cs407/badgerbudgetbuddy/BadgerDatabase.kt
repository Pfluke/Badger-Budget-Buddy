package com.cs407.badgerbudgetbuddy

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date


data class TransactionTotal(
    val amount: Float?,
    val type: String
)

// User Table
@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val userName: String = "",
    val hashedPassword: String = "",
    val checkingBalance: Double = 0.0,
    val savingBalance: Double = 0.0,
)
// Transaction Table
@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true) val transactionId: Int = 0,
    // Date is temporarily removed in order to test DB functionality
    //val date: Date,
    val amount: Double,
    val description: String,
    val type: String,
    val userId: Int = 1
)

@Entity
data class Receipts(
    @PrimaryKey(autoGenerate = true) val receiptId: Int = 0,
    val imageFilePath: String,
    val userId: Int
)

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }
}

@Dao
interface UserDao {
    @Insert
    fun insertUser(vararg userName: User)
    @Delete
    fun deleteUser(vararg userName: User)
}

@Dao
interface TransactionDao {
    @Insert
    fun insertTransaction(vararg transaction: com.cs407.badgerbudgetbuddy.Transaction)
    @Delete
    fun deleteTransaction(vararg transaction: com.cs407.badgerbudgetbuddy.Transaction)
    @Query("SELECT * FROM `Transaction`")
    fun getAllTransactions(): LiveData<List<Transaction>>
    @Query("DELETE FROM `Transaction`")
    fun deleteAllTransactions()
    @Query("SELECT SUM(amount) as amount, type FROM `Transaction` group by type")
    fun getTransactionTotals(): LiveData<List<TransactionTotal>>
}

@Database(entities = [User::class, Transaction::class, Receipts::class], version = 2)
@TypeConverters(Converters::class)
abstract class BadgerDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: BadgerDatabase? = null
        fun getDatabase(context: Context): BadgerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BadgerDatabase::class.java, "BadgerDatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}