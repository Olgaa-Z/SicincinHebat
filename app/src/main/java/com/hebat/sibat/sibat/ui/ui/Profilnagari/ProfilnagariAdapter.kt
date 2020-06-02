package com.hebat.sibat.sibat.ui.ui.Profilnagari

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
import kotlinx.android.synthetic.main.profilnagari_adapter.view.*

class ProfilnagariAdapter (private  val list:MutableList<ProfilnagariModel>, private val context: Context):
    RecyclerView.Adapter<ProfilnagariAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {


        val v = LayoutInflater.from(context).inflate(R.layout.profilnagari_adapter, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val item = list[p1]
        p0.profil.text = item.profil
        p0.profil.setOnClickListener {
            val intent = Intent(context, DetailProfilnagari::class.java)
            intent.putExtra(Config.id, item.id)
            intent.putExtra("from", "profilnagari")
            context.startActivity(intent)

        }
        Glide.with(context).load(Config.url_gambar_profil + item.gambar).into(p0.image)
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView =itemView.image
        val profil: TextView =itemView.profil
    }
}
