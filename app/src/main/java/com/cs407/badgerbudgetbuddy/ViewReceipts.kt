package com.cs407.badgerbudgetbuddy

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
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
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceContour
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.google.mlkit.vision.face.FaceLandmark
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.content.ContentResolver
import android.graphics.BitmapFactory
import com.cs407.badgerbudgetbuddy.BudgetViewModel
import com.cs407.badgerbudgetbuddy.BadgerDatabase

class ViewReceipts : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var receiptsRecyclerView: RecyclerView
    private lateinit var receiptsAdapter: ReceiptsAdapter
    private val CAMERA_REQUEST_CODE = 100
    private val imageUris = mutableListOf<Uri>()
    private var imageUri: Uri? = null
    private lateinit var vm: BudgetViewModel

    private val takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                imageUri?.let {
                    imageUris.add(it)
                    receiptsAdapter.notifyItemInserted(imageUris.size - 1)
                    onText(it)
                    Toast.makeText(requireContext(), "Successfully Processed Receipt", Toast.LENGTH_SHORT).show()
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
        vm = BudgetViewModel(requireActivity().application)

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

    fun onText(img: Uri) {
        // TODO: Implement the Basic Setup For Text Recognition
        Log.d("TextRec", "launched onText!")
        val bitmap = img.let { context?.let { it1 -> uriToBitmap(it1.contentResolver, it) } }
        val image = bitmap?.let { InputImage.fromBitmap(it, 0) }
        val option = TextRecognizerOptions.DEFAULT_OPTIONS
        val recognizer: TextRecognizer = TextRecognition.getClient(option)
        // TODO: Add Listeners for text detection process
        if (image != null) {
            recognizer.process(image).addOnSuccessListener { visionText ->
                val blocks = visionText.textBlocks
                if(blocks.isEmpty()) {
                    //toTextBox(getString(R.string.error), getString(R.string.text_recognition_error))
                }
                else {
                    val regex = Regex("\\d+\\.\\d+")

                    var largest: Double = 0.0
                    for (block in blocks) {
                        val matchResult = regex.find(block.text)
                        //if(block.text.contains(regex) && matchResult != null) {
                        if (matchResult != null) {
                            val amount = matchResult.value.toDouble()
                            if(amount > largest) {
                                largest = amount
                            }
                        }
                    }
                    val transaction = Transaction(amount = largest, description = "from Receipt", type = "Food")
                    if(vm != null) {vm.addTransaction(transaction)}
                }
            }.addOnFailureListener {
                //toTextBox(getString(R.string.error), getString(R.string.text_recognition_error))
            }
        }
    }

    fun uriToBitmap(contentResolver: ContentResolver, uri: Uri): Bitmap? {
        return try {
            contentResolver.openInputStream(uri)?.use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
