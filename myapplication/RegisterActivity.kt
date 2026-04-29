package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import android.content.Intent
import android.widget.Button

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val btnBack = findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val username = findViewById<EditText>(R.id.etUsername)
        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)
        val confirm = findViewById<EditText>(R.id.etConfirm)
        val radioGroup = findViewById<RadioGroup>(R.id.RadioGrupGender)
        val spinner = findViewById<Spinner>(R.id.Prodi)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {

            val user = username.text.toString()
            val mail = email.text.toString()
            val pass = password.text.toString()
            val conf = confirm.text.toString()

            val selectedId = radioGroup.checkedRadioButtonId
            val gender = if (selectedId != -1) {
                findViewById<RadioButton>(selectedId).text.toString()
            } else ""

            val prodi = spinner.selectedItem.toString()

            if (user.isEmpty() || mail.isEmpty() || pass.isEmpty() || conf.isEmpty()) {
                Toast.makeText(this, "Semua data harus diisi!", Toast.LENGTH_SHORT).show()
            } else if (pass != conf) {
                Toast.makeText(this, "Password tidak sama!", Toast.LENGTH_SHORT).show()
            } else {

                val sharedPref = getSharedPreferences("USER_DATA", MODE_PRIVATE)
                val editor = sharedPref.edit()

                editor.putString("username", user)
                editor.putString("password", pass)
                editor.putString("email", mail)
                editor.putString("gender", gender)
                editor.putString("prodi", prodi)
                editor.apply()

                Toast.makeText(this, "Berhasil Register!", Toast.LENGTH_LONG).show()

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}