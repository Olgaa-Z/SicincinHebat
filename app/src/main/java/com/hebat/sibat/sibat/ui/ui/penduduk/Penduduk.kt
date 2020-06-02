package com.hebat.sibat.sibat.ui.ui.penduduk

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.RequestHandler
import com.hebat.sibat.sibat.ui.ui.config.Config
import kotlinx.android.synthetic.main.penduduk.*
import org.json.JSONObject


class Penduduk : AppCompatActivity() {

    private var list:MutableList<PendudukModel>?=null
    private var pd: ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.penduduk)
        list= mutableListOf()
        get_data_penduduk().execute()

        val actionBar = supportActionBar
        actionBar!!.title = "Data Penduduk (Korong) "
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }


    inner class get_data_penduduk : AsyncTask<String, Void, String>(){

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@Penduduk,"","Memuat Penduduk",true,true)
        }

        override fun doInBackground(vararg params: String?): String {

            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_korong) //"http://192.168.43.93/newss/index.php/Webservice/select_berita"
            Log.d("String",result)
            return result
        }

        override fun onPostExecute(result: String?) {
            val objek= JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                val model= PendudukModel()
                model.id_korong=data.getString("id_korong")
                model.nama_korong=data.getString("nama_korong")

                list?.add(model)
                val adapter= list?.let {
                    PendudukAdapter(
                        it,
                        this@Penduduk
                    )
                }
                rc.layoutManager= LinearLayoutManager(this@Penduduk)
                rc.adapter=adapter
            }
            super.onPostExecute(result)
            pd?.dismiss()

        }

    }

//    inner class get_data_penduduk : AsyncTask<String, Void, String>(){
//
//        override fun onPreExecute() {
//            super.onPreExecute()
//            pd= ProgressDialog.show(this@Penduduk,"","Memuat Data",true,true)
//        }
//
//        override fun doInBackground(vararg params: String?): String {
//
//            val handler= RequestHandler()
//            val result=handler.sendGetRequest(Config.url_penduduk) //"http://192.168.43.93/newss/index.php/Webservice/select_berita"
//            Log.d("String",result)
//            return result
//        }
//
//        override fun onPostExecute(result: String?) {
//            val objek= JSONObject(result)
//            val array=objek.getJSONArray("data")
//            for (i in 0 until array.length()){
//                val data=array.getJSONObject(i)
//                val model= PendudukModel()
//                model.jumlah_penduduk=data.getString("jumlah_penduduk")
//                model.jumlah_lembaga=data.getString("jumlah_lembaga")
//                model.jumlah_programdesa=data.getString("jumlah_programdesa ")
//                list?.add(model)
//                val adapter= list?.let {
//                    PendudukAdapter(
//                        it,
//                        this@Penduduk
//                    )
//                }
//                rc.layoutManager= LinearLayoutManager(this@Penduduk)
//                rc.adapter=adapter
//            }
//            super.onPostExecute(result)
//
//        }
//
//    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
