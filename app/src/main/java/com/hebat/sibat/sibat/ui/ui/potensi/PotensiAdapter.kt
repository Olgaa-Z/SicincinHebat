package com.hebat.sibat.sibat.ui.ui.potensi

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.config.Config
import com.hebat.sibat.sibat.ui.ui.potensinagari.PotensinagariModel
import kotlinx.android.synthetic.main.potensinagari_adapter.view.*

class PotensiAdapter (private  val list:MutableList<PotensinagariModel>, private val context: Context):
    RecyclerView.Adapter<PotensiAdapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val item=list.get(p1)
        p0.korong.text=item.nama_korong
        p0.potensi.setOnClickListener {
            val i= Intent(context, DetailPotensi::class.java)
            i.putExtra("id",item.id)
            i.putExtra("from","Potensinagari")
            context.startActivity(i)
        }
        Glide.with(context).load(Config.url_gambar_potensi + item.gambar).into(p0.image)
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): ViewHolder {
        var  v : View
        v = LayoutInflater.from(parent.context).inflate(R.layout.potensinagari_adapter, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView =itemView.image
        val potensi: TextView =itemView.potensi
        val korong: TextView =itemView.korong
//        val profil: TextView =itemView.profil
    }
}

