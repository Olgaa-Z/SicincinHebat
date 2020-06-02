 package com.hebat.sibat.sibat.ui.ui.potensi

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

 class DetailPotensi : AppCompatActivity() {

     private var id:String?=null
     private var pd: ProgressDialog?=null
     private var from :String?=null

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.detail_potensinagari)

         id=intent.getStringExtra(Config.id)
         from=intent.getStringExtra("from")
         Toast.makeText(this@DetailPotensi, from, Toast.LENGTH_SHORT).show()
         if (from.equals("berita",true)){
             getdetail_potensi().execute()
         }else{
             getdetail_potensi().execute()
         }

         val actionBar = supportActionBar
         actionBar!!.title = "Detail Potensi Nagari"
         actionBar.setDisplayHomeAsUpEnabled(true)
         actionBar.setDisplayHomeAsUpEnabled(true)
     }


     inner class getdetail_potensi : AsyncTask<String, Void, String>(){

         override fun doInBackground(vararg params: String?): String {
             val request= RequestHandler()
             return request.sendGetRequest(Config.url_detail_potensidesa+id)

         }

         override fun onPreExecute() {
             super.onPreExecute()
             pd= ProgressDialog.show(this@DetailPotensi,"","Wait...",false,true)
         }


         override fun onPostExecute(result: String?) {
             super.onPostExecute(result)
             pd?.dismiss()
             val objek= JSONObject(result)
             if (objek.getInt("status")==1){
                 Toast.makeText(this@DetailPotensi, "Tidak ada data!", Toast.LENGTH_SHORT).show()
             }
             else {
                 val array = objek.getJSONArray("data")
                 for (i in 0 until array.length()) {
                     val data = array.getJSONObject(i)
                     potensi.text = data.getString("potensi")
                     korong.text = data.getString("nama_korong")
                     keterangan.text = data.getString("keterangan")
//                    tanggal.text = data.getString("tanggal")
                     Glide.with(this@DetailPotensi)
                         .load(Config.url_gambar_potensi+ data.getString("gambar")).into(gambarpotensi)
                 }
             }
         }


     }

     override fun onSupportNavigateUp(): Boolean {
         onBackPressed()
         return true
     }

}
