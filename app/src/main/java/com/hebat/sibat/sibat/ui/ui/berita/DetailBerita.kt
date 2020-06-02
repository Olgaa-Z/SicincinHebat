package com.hebat.sibat.sibat.ui.ui.berita

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.Toast
import com.bumptech.glide.Glide
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.RequestHandler
import com.hebat.sibat.sibat.ui.ui.config.Config
import kotlinx.android.synthetic.main.detail_berita.*
import kotlinx.android.synthetic.main.toolbardua.*
import org.json.JSONObject

class DetailBerita : AppCompatActivity() {

    private var id:String?=null
    private var pd: ProgressDialog?=null
    private var from :String?=null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_berita)

//        setSupportActionBar(tToolbar)
//        supportActionBar?.setDisplayShowHomeEnabled(true)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        var t: String = intent.getStringExtra("Title")
//        supportActionBar?.title =t


        id=intent.getStringExtra(Config.id)
        from=intent.getStringExtra("from")
        Toast.makeText(this@DetailBerita, from, Toast.LENGTH_SHORT).show()
        if (from.equals("berita",true)){
            getdetailberita().execute()
        }else{
            getdetailberita().execute()
        }

        val actionBar = supportActionBar
        actionBar!!.title = "Detail Berita"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    inner class getdetailberita : AsyncTask<String, Void, String>(){

        override fun doInBackground(vararg params: String?): String {
            val request= RequestHandler()
            return request.sendGetRequest(Config.url_detail_berita+id)

        }

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@DetailBerita,"","Wait...",false,true)
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek= JSONObject(result)
            if (objek.getInt("status")==1){
                Toast.makeText(this@DetailBerita, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            }
            else {
                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    judul.text = data.getString("judul")
                    isi.text = data.getString("isi")
                    kategori.text = data.getString("kategori")
                    tanggal.text = data.getString("tanggal")
                    Glide.with(this@DetailBerita)
                        .load(Config.url_gambar_berita+ data.getString("gambar")).into(gambarberita)
                }
            }
        }


    }

//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return true
//    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
