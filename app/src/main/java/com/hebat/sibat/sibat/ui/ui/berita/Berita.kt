package com.hebat.sibat.sibat.ui.ui.berita

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.RequestHandler
import com.hebat.sibat.sibat.ui.ui.config.Config
import kotlinx.android.synthetic.main.berita.*
import org.json.JSONArray
import org.json.JSONObject

class Berita : AppCompatActivity() {

    private var list:MutableList<BeritaModel>?=null
    private var pd: ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.berita)
        list= mutableListOf()
        get_data_berita().execute()

        val actionBar = supportActionBar
        actionBar!!.title = "Berita Nagari"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    inner class get_data_berita : AsyncTask<String, Void, String>(){

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@Berita,"","Memuat Berita",true,true)
        }
        override fun doInBackground(vararg params: String?): String {
            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_berita) //"http://192.168.43.93/newss/index.php/Webservice/select_berita"
            Log.d("String",result)
            return result
        }

        override fun onPostExecute(result: String?) {
            val objek= JSONObject(result)
            val array=objek.getJSONArray("data")
            for (i in 0 until array.length()){
                val data=array.getJSONObject(i)
                val model= BeritaModel()
                model.id=data.getString("id_berita")
                model.judul=data.getString("judul")
                model.isi=data.getString("isi")
                model.kategori=data.getString("kategori")
                model.gambar=data.getString("gambar")
                model.tanggal=data.getString("tanggal")
                list?.add(model)
                val adapter= list?.let {
                    BeritaAdapter(
                        it,
                        this@Berita
                    )
                }
                rc.layoutManager= LinearLayoutManager(this@Berita)
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

//class MainActivity : AppCompatActivity(), RecyclerTouchListener.ClickListener {
//
//
//    lateinit var recyclerView: RecyclerView
//    lateinit var linearLayoutManager: LinearLayoutManager
//    lateinit var beritas: ArrayList<BeritaModel>
//    lateinit var beritaModel: BeritaModel
//    lateinit var adapter: BeritaAdapter
//    lateinit var pd : ProgressDialog
//    lateinit var fieldCari : EditText
//    lateinit var cari : Button
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        recyclerView = findViewById(R.id.rc)
////        fieldCari = findViewById(R.id.cari_berita)
////        cari = findViewById(R.id.cari)
//
//
//
//        linearLayoutManager = LinearLayoutManager(this@MainActivity)
//        recyclerView.setHasFixedSize(true)
//        recyclerView.layoutManager = linearLayoutManager
//
//        beritas = ArrayList()
//        var berita = getBerita()
//        berita.execute()
//
//
//        recyclerView.addOnItemTouchListener(RecyclerTouchListener(this@MainActivity, recyclerView, this))
//
//
//        cari.setOnClickListener {
//            cariBerita().execute(fieldCari.text.toString())
//        }
//    }
//
//    override fun onLongClick(view: View?, position: Int) {
//
//    }
//
//    override fun onClick(view: View, position: Int) {
//        var id: TextView
//        id = view.findViewById(R.id.id_berita)
//
//        var intent: Intent = Intent(this@MainActivity, DetailBerita::class.java)
//        intent.putExtra(Config.id, id.text.toString())
//        startActivity(intent)
//
//    }
//
//    inner class getBerita : AsyncTask<String, String, String>() {
//        var requestHandler: RequestHandler = RequestHandler()
//
//        override fun onPreExecute() {
//            super.onPreExecute()
//            pd = ProgressDialog.show(this@MainActivity, "", "Sedang memuat", false, false)
//        }
//
//        override fun doInBackground(vararg params: String?): String? {
//            var response: String = requestHandler.sendGetRequest(Config.url_berita)
//
//            return response
//        }
//
//        override fun onPostExecute(result: String) {
//            super.onPostExecute(result)
//            Log.i("POST", result)
//            pd.dismiss()
//            try {
//                var jsonObject = JSONObject(result)
//                var content: JSONObject
//                var jsonArray: JSONArray = jsonObject.getJSONArray(Config.DATA_ARRAY)
//                if (jsonArray.length() > 0) {
//                    for (i in 0..jsonArray.length()) {
//                        content = jsonArray.getJSONObject(i)
//                        beritaModel = BeritaModel(
//                            content.getString("id"),
//                            content.getString("gambar"),
//                            content.getString("judul"),
//                            content.getString("isi_berita"),
//                            content.getString("tgl_berita"))
//
//                        beritas.add(beritaModel)
//                        adapter = BeritaAdapter(this@MainActivity, beritas)
//                        recyclerView.setAdapter(adapter)
//                    }
//                }
//            } catch (e: Exception) {
//
//            }
//        }
//
//
//    }
//
//    inner class cariBerita : AsyncTask<String, String, String>(){
//
//        var requestHandler : RequestHandler = RequestHandler()
//
//        override fun onPreExecute() {
//            super.onPreExecute()
//            pd = ProgressDialog.show(this@MainActivity, "", "Sedang Mencari...", false, false)
//        }
//
//        override fun doInBackground(vararg params: String?): String {
//            var response: String = requestHandler.sendGetRequest(Config.URL_CARI_BERITA + params[0])
//            Log.i("TSG", Config.URL_CARI_BERITA + params[0])
//            return response
//        }
//
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//            pd.dismiss()
//            try {
//                var jsonObject = JSONObject(result)
//                var content: JSONObject
//                var jsonArray: JSONArray = jsonObject.getJSONArray(Config.DATA_ARRAY)
//                if (jsonArray.length() > 0) {
//                    beritas.clear()
//                    adapter = BeritaAdapter(this@MainActivity, beritas)
//                    recyclerView.adapter = adapter
//                    adapter.notifyDataSetChanged()
//                    for (i in 0..jsonArray.length()) {
//                        content = jsonArray.getJSONObject(i)
//                        beritaModel = BeritaModel(
//                            content.getString("id"),
//                            content.getString("gambar"),
//                            content.getString("judul"),
//                            content.getString("isi_berita"),
//                            content.getString("tgl_berita"))
//
//                        beritas.add(beritaModel)
//                        adapter = BeritaAdapter(this@MainActivity, beritas)
//                        recyclerView.setAdapter(adapter)
//                        adapter.notifyDataSetChanged()
//                    }
//                }
//            } catch (e: Exception) {
//
//            }
//        }
//    }
//}

