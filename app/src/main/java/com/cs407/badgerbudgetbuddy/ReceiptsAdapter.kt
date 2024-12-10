package com.cs407.badgerbudgetbuddy

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ReceiptsAdapter(private val receipts: List<Uri>) : RecyclerView.Adapter<ReceiptsAdapter.ReceiptViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.receipt_card, parent, false)
        return ReceiptViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        val imageUri = receipts[position]
        holder.imageView.setImageURI(imageUri)
    }

    override fun getItemCount(): Int {
        return receipts.size
    }

    inner class ReceiptViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.receiptImageView)
    }
}
