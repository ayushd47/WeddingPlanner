package com.reshika.foodmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.reshika.foodmart.entity.Consumer
import com.reshika.foodmart.entity.Contact
import com.reshika.foodmart.repository.ContactRepository
import com.reshika.foodmart.repository.CustomerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactUs_Activity : AppCompatActivity() {
    private lateinit var fullname:EditText
    private lateinit var email:EditText
    private lateinit var phone:EditText
    private lateinit var message:EditText
    private lateinit var btnSubmit:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us_)
        fullname= findViewById(R.id.fullname)
        email= findViewById(R.id.email)
        phone= findViewById(R.id.phone)
        message= findViewById(R.id.message)
        btnSubmit= findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener{
            adduser()
        }

    }
    private fun adduser(){
        val fullname = fullname.text.toString()
        val email = email.text.toString()
        val phone = phone.text.toString()
        val message = message.text.toString()



        val contact = Contact(fullname = fullname, email = email, phone=phone, message=message)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val contactRepository = ContactRepository()
                val response = contactRepository.contactUser(contact)
                if (response.success == true) {

                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ContactUs_Activity, " Contact Successfully", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ContactUs_Activity, "Error registering user", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}