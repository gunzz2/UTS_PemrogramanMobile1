package com.example.myapplication

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog

class DaftarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daftar_seminar)

        val edtNama = findViewById<EditText>(R.id.edtNama)
        val edtHp = findViewById<EditText>(R.id.edtHp)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val spinner = findViewById<Spinner>(R.id.spinnerSeminar)
        val btn = findViewById<Button>(R.id.btnSubmit)

        btn.setOnClickListener {

            val nama = edtNama.text.toString()
            val hp = edtHp.text.toString()
            val email = etEmail.text.toString()
            val seminar = spinner.selectedItem.toString()
            val checkSetuju = findViewById<CheckBox>(R.id.checkSetuju)

            if (nama.isEmpty()) {
                edtNama.error = "Nama wajib diisi"
                return@setOnClickListener
            }

            if (hp.isEmpty()) {
                edtHp.error = "Nomor HP wajib diisi"
                return@setOnClickListener
            }

            if (nama.isEmpty() || hp.isEmpty()) {
                Toast.makeText(this, "Data belum lengkap", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                findViewById<EditText>(R.id.etEmail).error = "Email wajib diisi"
                return@setOnClickListener
            }

            if (hp.isEmpty()) {
                edtHp.error = "Nomor HP wajib diisi"
                return@setOnClickListener
            }

            if (!email.endsWith("@gmail.com")) {
                findViewById<EditText>(R.id.etEmail).error = "Email harus menggunakan @gmail.com"
                return@setOnClickListener
            }

            if (!hp.startsWith("08")) {
                edtHp.error = "Nomor HP harus diawali 08"
                return@setOnClickListener
            }

            if (hp.length < 11 || hp.length > 13) {
                edtHp.error = "Nomor HP harus 11 - 13 digit"
                return@setOnClickListener
            }

            if (!checkSetuju.isChecked) {
                Toast.makeText(this, "Anda harus menyetujui data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah data sudah sesuai?")
                .setPositiveButton("Ya") { _, _ ->

                    DataPeserta.list.add(Triple(nama, hp, seminar))

                    Toast.makeText(this, "Berhasil daftar!", Toast.LENGTH_SHORT).show()

                    edtNama.text.clear()
                    etEmail.text.clear()
                    edtHp.text.clear()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show() }

            val btnKembali = findViewById<Button>(R.id.btnKembali)

            btnKembali.setOnClickListener {
                finish()
            }
        }
    }
