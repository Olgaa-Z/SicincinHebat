package com.hebat.sibat.sibat.ui.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.ui.ui.config.Config
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.pengaduan.*

class Pengaduan : Fragment() {

    private var pd: ProgressDialog? = null
    private var list: MutableList<String>? = null
    private var listid: MutableList<String>? = null
    private var params:HashMap<String,String>?=null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_pengaduan)
        val view = inflater.inflate(R.layout.pengaduan, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(Prefs.contains("email")){   //session untuk ngecek kalo dia udah login munculin halaman pengaduan

//            aduan.visibility=View.VISIBLE  ///mengaktifkan edittext aduan
            startActivity(Intent(activity, Detailaduan::class.java))

        }
        buttonaduan.setOnClickListener{

            list = mutableListOf()
            listid = mutableListOf()
            getdataaduan().execute()

//            params= hashMapOf()// instance dari hashmap like mutableList
//            kirimaduan().execute()
//            params= hashMapOf ()
//                getdataaduan().execute()

            if(Prefs.contains("email")){   //session untuk ngecek kalo dia udah login munculin halaman pengaduan
                buttonaduan.setOnClickListener { startActivity(Intent(activity, Detailaduan::class.java)) }
//                aduan.visibility=View.VISIBLE  ///mengaktifkan edittext aduan

            }else{
                Toast.makeText(activity, "Login Dahulu", Toast.LENGTH_SHORT).show()
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, Login())
                    ?.commit()
            }
        }
    }

    inner class getdataaduan :  AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            pd= ProgressDialog.show(activity,"","sending",true,true)
        }

        override fun doInBackground(vararg args: String?): String {
            val username= Prefs.getString("email","")
//            val keluhan=aduan.text

            val handler= RequestHandler()
            params?.put("username",username)     //email.text.toString  ->kalo take data dari edittext //usernamekalo take dari session yg udah ada like session on login
//            params?.put("keluhan",keluhan.toString())

            val result= params?.let { handler.sendPostRequest(Config.url_pengaduan, it) }
            return result?:""

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd?.dismiss()

        }
    }
}
