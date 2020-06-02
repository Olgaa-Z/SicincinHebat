package com.hebat.sibat.sibat.ui.ui.Profilnagari

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.RequestHandler
import com.hebat.sibat.sibat.ui.ui.config.Config
import kotlinx.android.synthetic.main.profilnagari.*
import org.json.JSONObject

class Profilnagari : AppCompatActivity() {

    private var list: MutableList<ProfilnagariModel>? = null
    private var pd: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profilnagari)
        list = mutableListOf()
//        val execute = get_data_pemberitahuan().execute()
        get_data_profilnagari().execute()

        val actionBar = supportActionBar
        actionBar!!.title = "Profil Nagari"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    inner class get_data_profilnagari : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@Profilnagari,"","Wait",true,true)
        }


        override fun doInBackground(vararg params: String?): String {

            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_profilnagari)
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            pd?.dismiss()
            val objek= JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                val model= ProfilnagariModel()
                model.id=data.getString("id_profil")
                model.gambar=data.getString("gambar")
                model.profil=data.getString("profil")
                list?.add(model)
                val adapter= list?.let {
                    ProfilnagariAdapter(
                        it,
                        this@Profilnagari
                    )
                }
                rc.layoutManager= LinearLayoutManager(this@Profilnagari)
                rc.adapter=adapter
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
