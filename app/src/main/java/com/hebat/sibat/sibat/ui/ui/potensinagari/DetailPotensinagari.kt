package com.hebat.sibat.sibat.ui.ui.potensinagari

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.RequestHandler
import com.hebat.sibat.sibat.ui.ui.config.Config
import kotlinx.android.synthetic.main.detail_potensinagari.*
import org.json.JSONObject

class DetailPotensinagari : AppCompatActivity() {

    private var id:String?=null
    private var pd: ProgressDialog?=null
    private var from :String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_potensinagari)

        id=intent.getStringExtra(Config.id)
        from=intent.getStringExtra("from")
        Toast.makeText(this@DetailPotensinagari, from, Toast.LENGTH_SHORT).show()
        if (from.equals("berita",true)){
            getdetaillayanan().execute()
        }else{
            getdetaillayanan().execute()
        }

        val actionBar = supportActionBar
        actionBar!!.title = "Detail Potensi Nagari"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }


    inner class getdetaillayanan : AsyncTask<String, Void, String>(){

        override fun doInBackground(vararg params: String?): String {
            val request= RequestHandler()
            return request.sendGetRequest(Config.url_detail_potensidesa+id)

        }

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@DetailPotensinagari,"","Wait...",false,true)
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek= JSONObject(result)
            if (objek.getInt("status")==1){
                Toast.makeText(this@DetailPotensinagari, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            }
            else {
                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    korong.text = data.getString("nama_korong")
                    keterangan.text = data.getString("keterangan")
//                    tanggal.text = data.getString("tanggal")
                    Glide.with(this@DetailPotensinagari)
                        .load(Config.url_gambar_layanan+ data.getString("gambar")).into(gambarpotensi)
                }
            }
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
