package com.reshika.foodmartwearos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.reshika.foodmartwearos.api.ServiceBuilder
import com.reshika.foodmartwearos.repository.CustomerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : WearableActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var btnlogin: Button
    private lateinit var linearLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setAmbientEnabled()

        username = findViewById(R.id.usernamewear)
        password = findViewById(R.id.passwordwear)
        btnlogin = findViewById(R.id.btnloginwear)
        linearLayout = findViewById(R.id.linearLayout)

        btnlogin.setOnClickListener() {
            loginProccessForWearStart()
        }
    }
    private fun loginProccessForWearStart() {
        var user = username.text.toString()
        var pass = password.text.toString()
        if (username.text.toString().isEmpty()) {
            username.error = "Username required"
            Toast.makeText(this, "username required", Toast.LENGTH_SHORT).show()
            username.requestFocus()
        } else if (pass.isEmpty()) {
            password.error = "password required"
            Toast.makeText(this, "password required", Toast.LENGTH_SHORT).show()
            password.requestFocus()
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val repositary = CustomerRepository()
                    val response = repositary.checkUser(user, pass)
                    if (response.success == true) {
                        ServiceBuilder.token = "Bearer ${response.token}"
                        startActivity(Intent(this@LoginActivity, Dashboard_Activity::class.java))
                        finish()
                    } else {
                        withContext(Dispatchers.Main) {
                            val snack =
                                Snackbar.make(
                                    linearLayout,
                                    response.data.toString(),
                                    Snackbar.LENGTH_LONG
                                )
                            snack.setAction("OK", View.OnClickListener {
                                snack.dismiss()
                            })
                            snack.show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@LoginActivity,
                            ex.localizedMessage,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
}