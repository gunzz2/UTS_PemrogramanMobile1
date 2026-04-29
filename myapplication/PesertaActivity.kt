package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class PesertaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.data_seminar)

        val listView = findViewById<ListView>(R.id.listPeserta)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        tampilkanData(listView)

        btnKembali.setOnClickListener {
            finish()
        }
    }

    private fun tampilkanData(listView: ListView) {

        val groupedData = DataPeserta.list.groupBy { it.third }
        val tampil = mutableListOf<String>()

        if (groupedData.isEmpty()) {
            tampil.add("Belum ada peserta")
        } else {
            groupedData.forEach { (seminar, pesertaList) ->

                tampil.add("=== $seminar ===")

                pesertaList.forEach { peserta ->
                    val (nama, hp, _) = peserta
                    tampil.add("$nama - $hp")
                }
            }
        }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            tampil
        )

        listView.adapter = adapter
    }
}