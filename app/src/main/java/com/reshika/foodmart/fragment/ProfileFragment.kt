package com.reshika.foodmart.fragment

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import com.bumptech.glide.Glide
import com.reshika.foodmart.R
import com.reshika.foodmart.api.ServiceBuilder
import com.reshika.foodmart.repository.CustomerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class ProfileFragment : Fragment() {
    private lateinit var txtName: TextView
    private lateinit var txtUser: TextView
    private lateinit var txtAddress: TextView
    private lateinit var txtContact: TextView
    private lateinit var txtEmail: TextView
    private lateinit var itemImage: ImageView
    var username = ""
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        txtName = root.findViewById(R.id.txtName)
        txtUser = root.findViewById(R.id.txtUser)
        txtAddress = root.findViewById(R.id.txtAddress)
        txtContact = root.findViewById(R.id.txtContact)
        txtEmail = root.findViewById(R.id.txtEmail)

        itemImage = root.findViewById(R.id.itemImage)

        val data = ServiceBuilder.data!!
        val imagePath = ServiceBuilder.loadImagePath() + data[0].profileimg
        Glide.with(this@ProfileFragment)
                .load(imagePath)
                .into(itemImage)

        txtName.text = data[0].fullname
        txtUser.text = data[0].username
        txtAddress.text = data[0].address
        txtContact.text = data[0].contact
        txtEmail.text = data[0].email
        username = data[0].username.toString()

//        getProfile()
        itemImage.setOnClickListener {
            loadPopUpMenu()
        }

        return root
    }

    private fun loadPopUpMenu() {
        // Load pop up menu
        val popupMenu = context?.let { PopupMenu(it, itemImage) }
        if (popupMenu != null) {
            popupMenu.menuInflater.inflate(R.menu.gallery_camera, popupMenu.menu)
        }
        if (popupMenu != null) {
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menuCamera ->
                        openCamera()
                    R.id.menuGallery ->
                        openGallery()
                }
                true
            }
        }
        if (popupMenu != null) {
            popupMenu.show()
        }
    }

    private fun getProfile() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val customerRepository = CustomerRepository()
                val response = customerRepository.getCitizen()

                if (response.success == true) {
                    val listprofile = response.profile
                    if (listprofile != null) {
                        withContext(Dispatchers.Main) {
                            Log.d("Debug:", "Your data:" + listprofile[0])

                            txtName.text = listprofile[0].fullname
                            txtUser.text = listprofile[0].username
                            txtAddress.text = listprofile[0].address
                            txtContact.text = listprofile[0].contact.toString()
                            txtEmail.text = listprofile[0].email
                            username = listprofile[0].username.toString()

                            val imagePath = ServiceBuilder.loadImagePath() + listprofile[0].profileimg
                            Glide.with(this@ProfileFragment)
                                    .load(imagePath)
//                                    .fitCenter()
                                    .into(itemImage)

                        }

                    }

                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                            context,
                            "Error : ${ex.toString()}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private var REQUEST_GALLERY_CODE = 0
    private var REQUEST_CAMERA_CODE = 1
    private var imageUrl = ""
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY_CODE)
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CAMERA_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_GALLERY_CODE && data != null) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = requireActivity().contentResolver
                val cursor =
                        contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                imageUrl = cursor.getString(columnIndex)
                itemImage.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
                cursor.close()
            } else if (requestCode == REQUEST_CAMERA_CODE && data != null) {
                val imageBitmap = data.extras?.get("data") as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapToFile(imageBitmap, "$timeStamp.jpg")
                imageUrl = file!!.absolutePath
                itemImage.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
            }
        }
    }

    private fun bitmapToFile(bitmap: Bitmap, fileName: String): File? {
        var file: File? = null
        return try {
            file = File(
                    requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                            .toString() + File.separator + fileName
            )
            file.createNewFile()
            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitMapData = bos.toByteArray()
            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitMapData)
            fos.flush()
            fos.close()
            file
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }

    private fun uploadImage() {
        if (imageUrl != null) {
            val file = File(imageUrl!!)
            val reqFile =
                    RequestBody.create(
                            MediaType.parse(
                                    "image/" + file.extension.toLowerCase().replace("jpg", "jpeg")
                            ), file
                    )
            val body =
                    MultipartBody.Part.createFormData("photo", file.name, reqFile)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val customerRepository = CustomerRepository()
                    val response = customerRepository.uploadImage(username, body)
                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context, "Uploaded", Toast.LENGTH_SHORT)
                                    .show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.d("Mero Error ", ex.localizedMessage)
                        Toast.makeText(
                                context,
                                ex.localizedMessage,
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}
