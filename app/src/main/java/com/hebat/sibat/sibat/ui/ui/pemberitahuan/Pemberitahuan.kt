package com.hebat.sibat.sibat.ui.ui.pemberitahuan

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.RequestHandler
import com.hebat.sibat.sibat.ui.ui.config.Config
import kotlinx.android.synthetic.main.pemberitahuan.*
import kotlinx.android.synthetic.main.toolbardua.*
import org.json.JSONObject

class Pemberitahuan : AppCompatActivity() {

    private var list: MutableList<PemberitahuanModel>? = null
    private var pd: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pemberitahuan)
        list = mutableListOf()
//        val execute = get_data_pemberitahuan().execute()
        get_data_pemberitahuan().execute()

        val actionBar = supportActionBar
        actionBar!!.title = "Pemberitahuan"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }


    inner class get_data_pemberitahuan : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@Pemberitahuan,"","Wait",true,true)
        }


        override fun doInBackground(vararg params: String?): String {

            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_pengumuman)
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            pd?.dismiss()
            val objek= JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                val model= PemberitahuanModel()
                model.id=data.getString("id_pengumuman")
                model.gambar=data.getString("gambar")
                model.judul=data.getString("judul")
                model.tanggal=data.getString("tanggal")
                list?.add(model)
                val adapter= list?.let {
                    PemberitahuanAdapter(
                        it,
                        this@Pemberitahuan
                    )
                }
                rc.layoutManager= LinearLayoutManager(this@Pemberitahuan)
                rc.adapter=adapter
            }
        }





    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
