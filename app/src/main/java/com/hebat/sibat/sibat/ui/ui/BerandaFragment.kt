package com.hebat.sibat.sibat.ui.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.berita.Berita
import com.hebat.sibat.sibat.ui.ui.berita.BeritaModel
import com.hebat.sibat.sibat.ui.ui.berita.DetailBerita
import com.hebat.sibat.sibat.ui.ui.config.Config
import com.hebat.sibat.sibat.ui.ui.layanan.Layanan
import com.hebat.sibat.sibat.ui.ui.pemberitahuan.Pemberitahuan
import com.hebat.sibat.sibat.ui.ui.penduduk.Penduduk
import com.hebat.sibat.sibat.ui.ui.programdesa.Programnagari
import kotlinx.android.synthetic.main.beranda_fragment.*
import org.json.JSONObject

class BerandaFragment : Fragment() {

    private var pd: ProgressDialog? = null
    private var list: MutableList<String>? = null
    private var listid: MutableList<String>? = null
    private var listjudul: MutableList<String>? = null

    companion object {
        val TAG: String = BerandaFragment::class.java.simpleName
        fun newInstance() = BerandaFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_beranda)
        val view = inflater.inflate(R.layout.beranda_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        berita.setOnClickListener { startActivity(Intent(activity, Berita::class.java)) }
        layanan.setOnClickListener { startActivity(Intent(activity, Layanan::class.java)) }
        pemberitahuan.setOnClickListener { startActivity(Intent(activity, Pemberitahuan::class.java)) }
        maps.setOnClickListener { activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, PetanagariFragment())?.commit() }
        programnagari.setOnClickListener { startActivity(Intent(activity, Programnagari::class.java)) }
        penduduk.setOnClickListener { startActivity(Intent(activity, Penduduk::class.java)) }


        list = mutableListOf()
            listid = mutableListOf()
            listjudul = mutableListOf()
            get_data_imageslider().execute()
        }


    inner class get_data_imageslider : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            pd = ProgressDialog.show(activity, "", "Memuat Berita", true, true)
        }

        override fun doInBackground(vararg params: String?): String {

            val handler = RequestHandler()
            val result = handler.sendGetRequest(Config.url_berita)
            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()
            val objek = JSONObject(result)
            if (objek.getInt("status") == 1) {
                Toast.makeText(activity, "Tidak ada data!", Toast.LENGTH_SHORT).show()
            } else {
                val array = objek.getJSONArray("data")
                for (i in 0 until array.length()) {
                    val data = array.getJSONObject(i)
                    val model = BeritaModel()
                    model.id = data.getString("id_berita")
                    model.judul = data.getString("judul")
                    model.isi = data.getString("isi")
                    model.gambar = Config.url_gambar + data.getString("gambar")
                    model.tanggal = data.getString("tanggal")
                    list?.add(model.gambar ?: "")
                    listid?.add(model.id ?: "")
                    listjudul?.add(model.judul ?: "")

                }
                try {
                    setToImageSlider(list, listid)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

        private fun setToImageSlider(
            list: MutableList<String>?,
            listid: MutableList<String>?
        ) {
//                for(i in 0  until list?.size!!){
//                    val item=list.get(i)
            imageslider.setImageListener { position, imageView ->
                Log.d("gambar", list?.get(position).toString())
                activity?.let { Glide.with(it).load(list?.get(position)).into(imageView) }

            }
//                }
            imageslider.pageCount = list?.size ?: 0

            imageslider.setImageClickListener { it ->

                val intent = Intent(context, DetailBerita::class.java)
                intent.putExtra(Config.id, listid?.get(it))
                intent.putExtra("from", "berita")
                context?.startActivity(intent)

            }
        }

    }


}
