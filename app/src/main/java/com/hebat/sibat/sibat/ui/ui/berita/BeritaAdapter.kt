package com.hebat.sibat.sibat.ui.ui.berita

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.config.Config
import kotlinx.android.synthetic.main.beranda_fragment.view.*
import kotlinx.android.synthetic.main.berita_adapter.view.*

class BeritaAdapter  (private  val list:MutableList<BeritaModel>, private val context: Context):
RecyclerView.Adapter<BeritaAdapter.ViewHolder>() {



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {


        val v = LayoutInflater.from(context).inflate(R.layout.berita_adapter, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val item = list[p1]
        p0.judul.text = item.judul
        p0.tanggal.text = item.tanggal
        p0.klikteks.setOnClickListener {
            val intent = Intent(context, DetailBerita::class.java)
            intent.putExtra(Config.id, item.id)
            intent.putExtra("from", "berita")
            context.startActivity(intent)

        }
        Glide.with(context).load(Config.url_gambar_berita + item.gambar).into(p0.image)
    }


    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView =itemView.image
        val judul: TextView =itemView.judul
        val tanggal: TextView =itemView.tanggal
//        val btnread: Button =itemView.btnread
        val klikteks : TextView = itemView.kliktext
    }


}

//
//class BeritaAdapter(context: Context, beritaList: ArrayList<BeritaModel>) : RecyclerView.Adapter<BeritaAdapter.ViewHolder>() {
//
//    var context: Context = context
//    var beritaList: ArrayList<BeritaModel>
//
//    init {
//        this.beritaList = beritaList
//        this.context = context
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        var v: View
//        var layoutInflater: LayoutInflater
//
//        layoutInflater = LayoutInflater.from(parent.context)
//        v = layoutInflater.inflate(R.layout.berita_adapter, parent, false)
//
//        return ViewHolder(v)
//    }
//
//    override fun getItemCount(): Int {
//        return beritaList.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        var berita: BeritaModel = beritaList.get(position)
//
//        holder.tanggal.text = berita.tanggal
//        holder.judul.text = berita.judul
//        holder.id_berita.text = berita.id
//        Glide
//            .with(context)
//            .load(Config.url_gambar + berita.gambar)
//            .into(holder.gambar)
//
//    }
//
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var tanggal: TextView
//        var judul: TextView
//        var gambar: ImageView
//        var id_berita: TextView
//
//        init {
//            tanggal = itemView.findViewById(R.id.tanggal)
//            judul = itemView.findViewById(R.id.judul)
//            gambar = itemView.findViewById(R.id.image)
//            id_berita = itemView.findViewById(R.id.id_berita)
//
//
//        }
//    }
//}
