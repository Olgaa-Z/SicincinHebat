package com.hebat.sibat.sibat.ui.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hebat.sibat.sibat.R
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener


class LayananFragment : Fragment() {

//    lateinit var carouselView : CarouselView
//    val sampleImages= intArrayOf(R.drawable.satu,R.drawable.dua,R.drawable.tiga)
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//
//        activity?.title = getString(com.hebat.sibat.sibat.R.string.title_layanan)
//        val view: View
//        view = inflater.inflate(R.layout.layanan_fragment,null)
//
//        carouselView=view.findViewById(R.id.carouselView )
//        carouselView.setPageCount(3)
//        carouselView.setImageListener(imageListener)
//
//        carouselView.setImageClickListener { position ->
//            if (position==0){
//                val intent = Intent(getActivity(),DetailPeta::class.java)
//                intent?.putExtra("Title", "Pauh")
//                intent?.putExtra("URL", "https://goo.gl/maps/1nNKMBsyee92")
//                intent?.putExtra("Detail", getString(R.string.pauh))
//                startActivity(intent)
//            }else if (position==1){
//                val intent = Intent(getActivity(),DetailPeta::class.java)
//                intent?.putExtra("Title", "Sicincin")
//                intent?.putExtra("URL", "https://goo.gl/maps/A8ePoaKZAtH2")
//                intent?.putExtra("Detail", getString(R.string.sicincin))
//                startActivity(intent)
//            }
//            else{
//
//            }
//        }
//
//        return view
//    }
//
//    var imageListener: ImageListener = object : ImageListener {
//        override fun setImageForPosition(position: Int, imageView: ImageView) {
//            Glide.with(this@LayananFragment).load(sampleImages[position]).into(imageView)
//        }
//    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(com.hebat.sibat.sibat.R.string.title_layanan)
        val view = inflater.inflate(com.hebat.sibat.sibat.R.layout.layanan_adapter, container, false)
        return view
    }


}


