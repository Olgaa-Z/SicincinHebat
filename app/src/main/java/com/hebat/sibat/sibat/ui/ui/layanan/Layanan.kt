package com.hebat.sibat.sibat.ui.ui.layanan

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.RequestHandler
import com.hebat.sibat.sibat.ui.ui.config.Config
import kotlinx.android.synthetic.main.layanan.*
import org.json.JSONObject

class Layanan : AppCompatActivity() {

    private var list:MutableList<LayananModel>?=null
    private var pd: ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layanan)
        list= mutableListOf()
        get_data_layanan().execute()

        val actionBar = supportActionBar
        actionBar!!.title = "Layanan Nagari Sicincin"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    inner class get_data_layanan : AsyncTask<String, Void, String>(){

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@Layanan,"","Wait",true,true)
        }

        override fun doInBackground(vararg params: String?): String {

            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_layanan) //"http://192.168.43.93/newss/index.php/Webservice/select_berita"
            Log.d("String",result)
            return result
        }

        override fun onPostExecute(result: String?) {
            val objek= JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                val model= LayananModel()
                model.id=data.getString("id_layanan")
                model.judul=data.getString("nama_layanan")
                model.isi=data.getString("isi_layanan")
                model.gambar=data.getString("gambar")
//                model.tanggal=data.getString("tanggal")
                list?.add(model)
                val adapter= list?.let {
                    LayananAdapter(
                        it,
                        this@Layanan
                    )
                }
                rcl.layoutManager= LinearLayoutManager(this@Layanan)
                rcl.adapter=adapter
            }
            super.onPostExecute(result)
            pd?.dismiss()

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
