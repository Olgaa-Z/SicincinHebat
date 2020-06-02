package com.hebat.sibat.sibat.ui.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hebat.sibat.sibat.ui.ui.Profilnagari.Profilnagari
import com.hebat.sibat.sibat.ui.ui.lembaga.Lembaga
import com.hebat.sibat.sibat.ui.ui.potensi.Potensi
import com.hebat.sibat.sibat.ui.ui.potensinagari.Potensinagari
import kotlinx.android.synthetic.main.lainnya_fragment.*

class LainnyaFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    activity?.title = getString(com.hebat.sibat.sibat.R.string.title_lainnya)
    val view = inflater.inflate(com.hebat.sibat.sibat.R.layout.lainnya_fragment, container, false)
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    video.setOnClickListener { startActivity(Intent(activity, Video::class.java)) }
    profilnagari.setOnClickListener { startActivity(Intent(activity, Profilnagari::class.java)) }
    tentang.setOnClickListener { startActivity(Intent(activity, Tentang::class.java)) }
//    privacypolicy.setOnClickListener { startActivity(Intent(activity, Privacy::class.java)) }
    lembaga.setOnClickListener { startActivity(Intent(activity, Lembaga::class.java)) }
    potensinagari.setOnClickListener { startActivity(Intent(activity, Potensi::class.java)) }
  }

}