package com.cs407.badgerbudgetbuddy

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class ViewReceipts : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var receiptsRecyclerView: RecyclerView
    private lateinit var receiptsAdapter: ReceiptsAdapter
    private val CAMERA_REQUEST_CODE = 100
    private val imageUris = mutableListOf<Uri>()
    private var imageUri: Uri? = null

    private val takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                imageUri?.let {
                    imageUris.add(it)
                    receiptsAdapter.notifyItemInserted(imageUris.size - 1)
                }
            } else {
                Toast.makeText(requireContext(), "Image capture failed", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.view_receipts, container, false)
        val backBtn: Button = view.findViewById<Button>(R.id.back2)

        imageView = view.findViewById(R.id.cameraCapture)

        val navController = findNavController()

        val captureButton: Button = view.findViewById(R.id.open_camera)
        captureButton.setOnClickListener {
            openCamera()
        }

        backBtn.setOnClickListener {
            navController.navigate(R.id.action_viewReceipts_to_home)
        }

        receiptsRecyclerView = view.findViewById(R.id.receiptsRecyclerView)
        receiptsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        receiptsAdapter = ReceiptsAdapter(imageUris)
        receiptsRecyclerView.adapter = receiptsAdapter

        return view
    }

    private fun openCamera() {
        val imageFile = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "receipt_${System.currentTimeMillis()}.jpg")
        imageUri = FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}.fileprovider", imageFile)

        if (imageUri != null) {
            takePictureLauncher.launch(imageUri)
        } else {
            Toast.makeText(requireContext(), "Could not create file for image", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            imageUri?.let {
                imageView.setImageURI(it)
            }
        } else {
            Toast.makeText(requireContext(), "Image capture failed", Toast.LENGTH_SHORT).show()
        }
    }
}
