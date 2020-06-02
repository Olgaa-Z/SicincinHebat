package com.hebat.sibat.sibat.ui.ui.Profilnagari

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.RequestHandler
import com.hebat.sibat.sibat.ui.ui.config.Config
import kotlinx.android.synthetic.main.detail_profilnagari.*
import org.json.JSONObject

class DetailProfilnagari : AppCompatActivity() {

    private var id:String?=null
    private var pd: ProgressDialog?=null
    private var from :String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_profilnagari)

        id=intent.getStringExtra(Config.id)
        from=intent.getStringExtra("from")
        Toast.makeText(this@DetailProfilnagari, from, Toast.LENGTH_SHORT).show()
        if (from.equals("berita",true)){
            getdetailprofilnagari().execute()
        }else{
            getdetailprofilnagari().execute()
        }

        val actionBar = supportActionBar
        actionBar!!.title = "Detail Profil Nagari"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    inner class getdetailprofilnagari : AsyncTask<String, Void, String>(){
        override fun doInBackground(vararg params: String?): String {
            val request= RequestHandler()
            return request.sendGetRequest(Config.url_detail_profilnagari+id)

        }

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@DetailProfilnagari,"","Wait...",false,true)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek= JSONObject(result)
            if (objek.getInt("status")==1){
                Toast.makeText(this@DetailProfilnagari, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            }
            else {
                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    profil.text = data.getString("profil")
                    isi.text = data.getString("isi")
                    Glide.with(this@DetailProfilnagari)
                        .load(Config.url_gambar_profil+ data.getString("gambar")).into(gambarprofil)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
