package com.hebat.sibat.sibat.ui.ui.potensinagari

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
import kotlinx.android.synthetic.main.detail_potensinagari.view.*
import kotlinx.android.synthetic.main.potensinagari_adapter.view.*

class PotensinagariAdapter (private  val list:MutableList<PotensinagariModel>, private val context: Context):
    RecyclerView.Adapter<PotensinagariAdapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val item=list.get(p1)
        p0.keterangan.text=item.keterangan
        p0.keterangan.setOnClickListener {
            val i= Intent(context, DetailPotensinagari::class.java)
            i.putExtra("id",item.id)
            i.putExtra("from","Potensinagari")
            context.startActivity(i)
        }
        Glide.with(context).load(Config.url_gambar_layanan + item.gambar).into(p0.image)
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
        val keterangan: TextView =itemView.keterangan
//        val profil: TextView =itemView.profil
    }
}
