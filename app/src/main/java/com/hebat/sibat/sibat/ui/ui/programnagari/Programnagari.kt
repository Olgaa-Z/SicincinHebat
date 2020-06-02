package com.hebat.sibat.sibat.ui.ui.programdesa

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.RequestHandler
import com.hebat.sibat.sibat.ui.ui.config.Config
import com.hebat.sibat.sibat.ui.ui.programnagari.ProgramnagariModel
import kotlinx.android.synthetic.main.programnagari.*
import org.json.JSONObject

class Programnagari : AppCompatActivity() {

    private var list: MutableList<ProgramnagariModel>? = null
    private var pd: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.programnagari)

        list = mutableListOf()
        get_data_programnagari().execute()

        val actionBar = supportActionBar
        actionBar!!.title = "Program Nagari"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    inner class get_data_programnagari : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {   //method from asyntask, ecxecuted in first thread before Async excecution
            super.onPreExecute()
            pd= ProgressDialog.show(this@Programnagari,"","Wait",true,true)
        }

        override fun doInBackground(vararg params: String?): String {    //method Async

            val handler= RequestHandler()
            val result=handler.sendGetRequest(Config.url_programnagari)
            return result
        }

        override fun onPostExecute(result: String?) {   //method Async result
            super.onPostExecute(result)
            pd?.dismiss()
            try{
                val objek= JSONObject(result)
                val array=objek.getJSONArray("data")
                for (i in 0 until array.length()){
                    val data=array.getJSONObject(i)
                    val model= ProgramnagariModel()
                    model.id_program=data.getString("id_program")
                    model.nama_korong=data.getString("nama_korong")
                    model.pekerjaan=data.getString("pekerjaan")
                    model.keterangan=data.getString("keterangan")
                    list?.add(model)
                    val adapter= list?.let { ProgramnagariAdapter(this@Programnagari, it) }
                    rv.layoutManager= LinearLayoutManager(this@Programnagari)
                    rv.adapter=adapter
                }
            }catch (e:Exception){
                e.printStackTrace()
            }

        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
