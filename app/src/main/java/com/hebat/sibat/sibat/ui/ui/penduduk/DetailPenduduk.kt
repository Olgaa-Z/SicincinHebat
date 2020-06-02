package com.hebat.sibat.sibat.ui.ui.penduduk

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.RequestHandler
import com.hebat.sibat.sibat.ui.ui.config.Config
import kotlinx.android.synthetic.main.detail_penduduk.*
import org.json.JSONObject

class DetailPenduduk : AppCompatActivity() {

    private var id_korong :String?=null
    private var iddua :String?=null
    private var pd: ProgressDialog?=null
    private var from :String?=null



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_penduduk)

        id_korong=intent.getStringExtra(Config.id_korong)
        iddua = intent.getStringExtra("id_korong")
        from=intent.getStringExtra("from")
        Toast.makeText(this@DetailPenduduk, from, Toast.LENGTH_SHORT).show()
        if (from.equals("penduduk",true)){
            getdetailpenduduk().execute()
        }else{
            getdetailpenduduk().execute()
        }

        val actionBar = supportActionBar
        actionBar!!.title = "Detail Penduduk"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    inner class getdetailpenduduk : AsyncTask<String, Void, String>(){

        override fun doInBackground(vararg params: String?): String {
            val request= RequestHandler()
            return request.sendGetRequest(Config.url_detail_korong+iddua)

        }

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(this@DetailPenduduk,"","Wait...",false,true)
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek= JSONObject(result)
            if (objek.getInt("status")==1){
                Toast.makeText(this@DetailPenduduk, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            }
            else {

                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    jp.text = data.getString("jumlah_penduduk")
//                    jl.text = data.getString("jumlah_lembaga")
//                    jpd.text = data.getString("jumlah_programdesa")
                    perempuan.text = data.getString("perempuan")
                    lakilaki.text = data.getString("lakilaki")
                    tidaktamatsd.text = data.getString("tidaktamatsd")
                    tamatsd.text = data.getString("tamatsd")
                    tamatsmp.text = data.getString("tamatsmp")
                    tamatsma.text = data.getString("tamatsma")
                    tamatsarjana.text = data.getString("tamatsarjana")
                    islam.text = data.getString("islam")
                    kristen.text = data.getString("kristen")
                    khatolik.text = data.getString("khatolik")
                    hindu.text = data.getString("hindu")
                    budha.text = data.getString("budha")
                    konghuchu.text = data.getString("konghuchu")
                    menikah.text = data.getString("menikah")
                    belummenikah.text = data.getString("belummenikah")
//                    konghuchu.text = data.getString("konghuchu")
                    petani.text = data.getString("petani")
                    wiraswasta.text = data.getString("wiraswasta")
                    pns.text = data.getString("pns")
                    buruh.text = data.getString("buruh")
                    belumbekerja.text = data.getString("belumbekerja")
//                    nama_lembaga.text = data.getString("nama_lembaga")
//                    Glide.with(this@DetailPenduduk)
//                        .load(Config.url_gambar+ data.getString("gambar")).into(gambarberita)
                }

            }
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}
