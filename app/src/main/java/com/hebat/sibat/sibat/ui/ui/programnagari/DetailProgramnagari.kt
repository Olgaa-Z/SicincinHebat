package com.hebat.sibat.sibat.ui.ui.programnagari

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.RequestHandler
import com.hebat.sibat.sibat.ui.ui.config.Config
import kotlinx.android.synthetic.main.detail_programnagari.*
import org.json.JSONObject

class DetailProgramnagari : AppCompatActivity() {

    private var id:String?=null
    private var pd: ProgressDialog?=null
    private var from :String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_programnagari)

        id=intent.getStringExtra(Config.id)
        from=intent.getStringExtra("from")
        Toast.makeText(this@DetailProgramnagari, from, Toast.LENGTH_SHORT).show()
        if (from.equals("lembaga",true)){
            getdetailprogramnagari().execute()
        }else{
            getdetailprogramnagari().execute()
        }

        val actionBar = supportActionBar
        actionBar!!.title = "Detail Program Nagari"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }


    inner class getdetailprogramnagari : AsyncTask<String, Void, String>(){

        override fun doInBackground(vararg params: String?): String {
            val request= RequestHandler()
            return request.sendGetRequest(Config.url_detail_programnagari+id)

        }

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@DetailProgramnagari,"","Wait...",false,true)
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek= JSONObject(result)
            if (objek.getInt("status")==1){
                Toast.makeText(this@DetailProgramnagari, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            }
            else {
                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    pekerjaan.text = data.getString("pekerjaan")
                    lokasi.text = data.getString("nama_korong")
                    keterangan.text = data.getString("keterangan")
                }
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
