package com.hebat.sibat.sibat.ui.ui

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.hebat.sibat.sibat.R

class DetailPeta : AppCompatActivity() {
    lateinit var tv_title: TextView
    lateinit var tv_detail: TextView
    lateinit var iv_gmbr: ImageView

    private var fab: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_peta)

        val actionBar = supportActionBar
        actionBar!!.title = "Detail Peta"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        iv_gmbr=findViewById(R.id.gambar) as ImageView
        tv_title=findViewById(R.id.judul)
        tv_detail=findViewById(R.id.penjelasan)
        var t: String = intent.getStringExtra("Title")
        var d: String = intent.getStringExtra("Detail")
        var url: String = intent.getStringExtra("URL")
        supportActionBar?.title =t
        tv_title.setText(t)
        tv_detail.setText(d)
        var a=tv_title.text
        if (a=="Ladang Laweh"){
            iv_gmbr.setImageResource(R.drawable.ld)
        }else if (a=="Bari"){
            iv_gmbr.setImageResource(R.drawable.bari)
        }else if (a=="Sicincin"){
            iv_gmbr.setImageResource(R.drawable.ac)
        }
        else{
            iv_gmbr.setImageResource(R.drawable.pauh)
        }

        fab = findViewById(R.id.fab) as FloatingActionButton
        fab!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(url)
                )
                startActivity(intent)
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
