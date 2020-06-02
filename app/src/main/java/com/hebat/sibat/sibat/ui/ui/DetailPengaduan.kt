package com.hebat.sibat.sibat.ui.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.hebat.sibat.sibat.R
import kotlinx.android.synthetic.main.detail_pengaduan.*

class DetailPengaduan : AppCompatActivity() {

    lateinit var button: Button
    lateinit var editnama : EditText
    lateinit var editemail : EditText
    lateinit var editnomor : EditText
    lateinit var editpesan : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_pengaduan)

        editnama = findViewById(R.id.editnama)
        editemail = findViewById(R.id.editemail)
        editnomor = findViewById(R.id.editnomor)
        editpesan = findViewById(R.id.editpesan)
        button = findViewById(R.id.btnkirim)

        btnkirim.setOnClickListener {
            val intent = Intent(this@DetailPengaduan,  DetailPengaduan::class.java)
            startActivity(intent)

            val pesan1 = editnama.text.toString()
            val pesan2 = editemail.text.toString()
            val pesan3 = editnomor.text.toString()
            val pesan4 = editpesan.text.toString()
            val semuapesan = "Nama: $pesan1\nEmail : $pesan2\nNo. HP : $pesan3\nPesan : $pesan4"
			
            val kirimWA = Intent(Intent.ACTION_SEND)
            kirimWA.setType("text/plain");
            kirimWA.putExtra(Intent.EXTRA_TEXT, semuapesan);
            kirimWA.putExtra("jid", "6285362849684"+ "@s.whatsapp.net");
            kirimWA.setPackage("com.whatsapp");
            startActivity(kirimWA);
        }


    }
}
