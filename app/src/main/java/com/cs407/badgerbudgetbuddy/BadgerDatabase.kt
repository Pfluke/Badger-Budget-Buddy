package com.cs407.badgerbudgetbuddy

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Transaction
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date



// Define your own @Entity, @Dao and @Database
@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,

    val userName: String = "",
    val hashedPassword: String = "",
    val checkingBalance: Double = 0.0,
    val savingBalance: Double = 0.0,
)

@Entity(
    primaryKeys = ["userId"],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Transaction(
    val date: Date,
    val amount: Double,
    val description: String,
    val type: String,
    val userId: Int
)

@Entity(
    primaryKeys = ["userId"],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Receipts(
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
    suspend fun insertUser(vararg userName: User)
    @Delete
    suspend fun deleteUser(vararg userName: User)
}

@Dao
interface TransactionDao {
    @Insert
    suspend fun insertTransaction(vararg transaction: com.cs407.badgerbudgetbuddy.Transaction)
    @Delete
    suspend fun deleteTransaction(vararg transaction: com.cs407.badgerbudgetbuddy.Transaction)
}

@Database(entities = [User::class, Transaction::class, Receipts::class], version = 1)
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