package com.hebat.sibat.sibat.ui.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.berita.Berita
import kotlinx.android.synthetic.main.beranda_fragment.*
import kotlinx.android.synthetic.main.pengaduan_fragment.*

class PengaduanFragment : Fragment() {

    private var pd: ProgressDialog? = null
    private var list: MutableList<String>? = null
    private var listid: MutableList<String>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_pengaduan)
        val view = inflater.inflate(R.layout.pengaduan_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnlaporan.setOnClickListener { startActivity(Intent(activity, DetailPengaduan::class.java)) }


        }




}