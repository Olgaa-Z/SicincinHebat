package com.hebat.sibat.sibat.ui.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hebat.sibat.sibat.R
import com.hebat.sibat.sibat.Register
import com.hebat.sibat.sibat.ui.ui.config.Config
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.login.*
import org.json.JSONObject

class Loginn : Fragment()  {

    private var pd: ProgressDialog?=null
    private var params:HashMap<String,String>?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(com.hebat.sibat.sibat.R.string.title_login)
        return inflater.inflate(R.layout.login,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        params= hashMapOf()
        login.setOnClickListener {get_data_login().execute() }

        signuppengaduan.setOnClickListener {
            //            activity?.supportFragmentManager?.beginTransaction()
//                    ?.replace(R.id.container,Signup())
//                    ?.commit()
            startActivity(Intent(activity, Register::class.java))
        }


    }


    inner class get_data_login : AsyncTask<String, Void, String>(){

        override fun onPreExecute() {   //method from asyntask, ecxecuted in first thread before Async excecution
            super.onPreExecute()
            pd= ProgressDialog.show(activity,"","Wait",true,true)
        }

        override fun doInBackground(vararg param: String?): String {    //method Async

            val handler= RequestHandler()
            params?.put("email",email.text.toString())
            val put = params?.put("password", password.text.toString())

            val result= params?.let { handler.sendPostRequest(Config.url_login, it) }
            return result?:""
        }

        override fun onPostExecute(result: String?) {   //method Async result
            super.onPostExecute(result)
            pd?.dismiss()
            try{
                Log.d("result",result)
                val objek= JSONObject(result)
                if (objek.getInt("status")==1){
                    Toast.makeText(activity, "Login Gagal!", Toast.LENGTH_SHORT).show()
                }else{
                    Prefs.putString("email",email.text.toString())
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.container,ProfilFragment())
                        ?.commit()
                    Toast.makeText(activity, "Berhasil Login!", Toast.LENGTH_SHORT).show()
                }
            }catch(e:Exception){
                e.printStackTrace()
            }

        }

    }
}