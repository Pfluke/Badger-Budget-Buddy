package com.cs407.badgerbudgetbuddy

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class RecentTransactions: BaseActivity()  {
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recent_transactions) // Ensure you have a layout file for this activity
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.bucky -> {
//                Toast.makeText(this, "Hi Badger!", Toast.LENGTH_SHORT).show()
//                true
//            }
//            // logout of account
//            R.id.item2 -> {
//                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
//                true
//            }
//            // To switch to recent transactions
//            R.id.subitem1 -> {
//                // Toast.makeText(this, "SubItem 1 selected", Toast.LENGTH_SHORT).show()
//                val recentTransactionsIntent = Intent(this, RecentTransactions::class.java)
//                startActivity(recentTransactionsIntent)
//                true
//            }
//            R.id.subitem2 -> {
//                Toast.makeText(this, "SubItem 2 selected", Toast.LENGTH_SHORT).show()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}