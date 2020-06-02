package com.hebat.sibat.sibat.ui.ui.penduduk

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.config.Config
import kotlinx.android.synthetic.main.penduduk_adapter.view.*

class PendudukAdapter (private  val list:MutableList<PendudukModel>, private val context: Context):
    RecyclerView.Adapter<PendudukAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PendudukAdapter.ViewHolder {


        val v = LayoutInflater.from(context).inflate(R.layout.penduduk_adapter, p0, false)
        return PendudukAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val item = list[p1]
        p0.namakorong.text = item.nama_korong
        p0.namakorong.setOnClickListener {
            val intent = Intent(context, DetailPenduduk::class.java)
            intent.putExtra(Config.id, item.id_korong)
            intent.putExtra("from", "penduduk")
            intent.putExtra("id_korong", item.id_korong)
            context.startActivity(intent)

        }
//        Glide.with(context).load(Config.url_gambar + item.gambar).into(p0.image)
    }


    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val namakorong: TextView =itemView.namakorong
    }
}
