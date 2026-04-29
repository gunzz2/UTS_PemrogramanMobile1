package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import android.widget.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnKeluar = findViewById<Button>(R.id.btnKeluar)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnLogin.setOnClickListener {

            val inputUser = etUsername.text.toString().trim()
            val inputPass = etPassword.text.toString().trim()

            if (inputUser.isEmpty() || inputPass.isEmpty()) {
                Toast.makeText(this, "Username dan Password wajib diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPref = getSharedPreferences("USER_DATA", MODE_PRIVATE)
            val savedUser = sharedPref.getString("username", "")
            val savedPass = sharedPref.getString("password", "")

            if (savedUser.isNullOrEmpty() || savedPass.isNullOrEmpty()) {
                Toast.makeText(this, "Belum ada akun, silakan register!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (inputUser == savedUser && inputPass == savedPass) {
                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, MenuActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Username atau Password salah!", Toast.LENGTH_SHORT).show()
            }
        }

        btnKeluar.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Keluar")
                .setMessage("Yakin ingin keluar dari aplikasi?")
                .setPositiveButton("Ya") { _, _ ->
                    finishAffinity()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }

        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}