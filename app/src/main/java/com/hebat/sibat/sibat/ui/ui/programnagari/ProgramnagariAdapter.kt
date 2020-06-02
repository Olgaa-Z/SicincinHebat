package com.hebat.sibat.sibat.ui.ui.programdesa

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.programnagari.DetailProgramnagari
import com.hebat.sibat.sibat.ui.ui.programnagari.ProgramnagariModel

class ProgramnagariAdapter (private var context: Context, private var list:MutableList<ProgramnagariModel>): RecyclerView.Adapter<ProgramnagariAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): ViewHolder {
        var  v : View
        v = LayoutInflater.from(parent.context).inflate(R.layout.programnagari_adapter, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=list.get(position)
        holder.judul.text=item.pekerjaan
        holder.judul.setOnClickListener {
            val i= Intent(context, DetailProgramnagari::class.java)
            i.putExtra("id",item.id_program)
            i.putExtra("from","Programnagari")
            context.startActivity(i)
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        var judul : TextView

        init {
            judul = itemView!!.findViewById(R.id.txtkarangtaruna)
        }

    }
}
