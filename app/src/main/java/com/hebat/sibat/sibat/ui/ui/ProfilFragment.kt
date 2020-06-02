package com.hebat.sibat.sibat.ui.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hebat.sibat.sibat.R
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.detail_profil.*

class ProfilFragment : Fragment() {

    companion object {
        val TAG: String = ProfilFragment::class.java.simpleName
        fun newInstance() = ProfilFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_profil)
        return inflater.inflate(R.layout.detail_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        profildesa.setOnClickListener { startActivity(Intent(activity, ProfilDesa::class.java)) }
//        lembaga.setOnClickListener { startActivity(Intent(activity, Lembaga::class.java)) }
//        datadesa.setOnClickListener { startActivity(Intent(activity, DataDesa::class.java)) }

//        profilxml.setOnClickListener {
//
//            Toast.makeText(activity, "Profile", Toast.LENGTH_SHORT).show()
//        }




        email.text="Username anda : " + Prefs.getString("email", "")
        logout.setOnClickListener { Prefs.clear()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container,Login())
                ?.commit()
        }



    }

}
