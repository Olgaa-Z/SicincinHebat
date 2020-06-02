package com.hebat.sibat.sibat.ui.ui.potensi

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.RequestHandler
import com.hebat.sibat.sibat.ui.ui.config.Config
import com.hebat.sibat.sibat.ui.ui.potensinagari.PotensinagariModel
import kotlinx.android.synthetic.main.potensinagari.*
import org.json.JSONObject

class Potensi : AppCompatActivity() {

    private var list:MutableList<PotensinagariModel>?=null
    private var pd: ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.potensinagari)

        list= mutableListOf()
        get_data_potensi().execute()

        val actionBar = supportActionBar
        actionBar!!.title = "Potensi Nagari"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    inner class get_data_potensi : AsyncTask<String, Void, String>(){

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@Potensi,"","Wait",true,true)
        }

        override fun doInBackground(vararg params: String?): String {

            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_potensidesa) //"http://192.168.43.93/newss/index.php/Webservice/select_berita"
            Log.d("String",result)
            return result
        }

        override fun onPostExecute(result: String?) {
            val objek= JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                val model= PotensinagariModel()
                model.id=data.getString("id_potensi")
                model.potensi=data.getString("potensi")
                model.gambar=data.getString("gambar")
                model.nama_korong=data.getString("nama_korong")
                model.keterangan=data.getString("keterangan")
//                model.tanggal=data.getString("tanggal")
                list?.add(model)
                val adapter= list?.let {
                    PotensiAdapter(
                        it,
                        this@Potensi
                    )
                }
                rc.layoutManager= LinearLayoutManager(this@Potensi)
                rc.adapter=adapter
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
