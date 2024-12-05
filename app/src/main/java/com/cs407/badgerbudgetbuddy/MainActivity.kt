package com.cs407.badgerbudgetbuddy

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val navController = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.findNavController()
//            ?: throw IllegalStateException("NavController could not be found")
//
//
//        val switchAccountButton: Button = findViewById(R.id.switchAcct)
//        val viewReceiptsButton: Button = findViewById(R.id.viewReceipt)
//        val viewRecentTransacButton: Button = findViewById(R.id.viewRecentTransac)
//
//        switchAccountButton.setOnClickListener {
//            navController.navigate(R.id.switchAccountFragment)
//        }
//
//        viewReceiptsButton.setOnClickListener {
//            navController.navigate(R.id.viewReceipts)
//        }
//
//        viewRecentTransacButton.setOnClickListener {
//            navController.navigate(R.id.viewRecentTransac)
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    //TODO Below items will need to swap to a different activity

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.bucky -> {
                Toast.makeText(this, "Hi Badger", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.item2 -> {
                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.subitem1 -> {
                Toast.makeText(this, "SubItem 1 selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.subitem2 -> {
                Toast.makeText(this, "SubItem 2 selected", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}