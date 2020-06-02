package com.hebat.sibat.sibat.ui.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hebat.sibat.sibat.Aduan
import com.hebat.sibat.sibat.R
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity() {

//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {item->
//        when(item.itemId){
//            R.id.navigation_beranda -> {
//                println("home pressed")
//                replaceFragment(BerandaFragment())
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_layanan -> {
//                println("map pressed")
//                replaceFragment(LayananFragment())
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_petanagari -> {
//                println("cart pressed")
//                replaceFragment(PetanagariFragment())
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_pengaduan -> {
//                println("cart pressed")
//                replaceFragment(PengaduanFragment())
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_lainnya -> {
//                println("cart pressed")
//                replaceFragment(LainnyaFragment())
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//
//        false
//
//    }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        bottom.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//        replaceFragment(BerandaFragment())
//    }
//
//
//    private fun replaceFragment(fragment: Fragment){
//        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.container, fragment)
//        fragmentTransaction.commit()
//    }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(com.hebat.sibat.sibat.R.layout.activity_main)
    supportFragmentManager.beginTransaction()
      .replace(com.hebat.sibat.sibat.R.id.container, BerandaFragment())
      .commit()

    bottom.setOnNavigationItemSelectedListener {
      when (it.itemId){
        com.hebat.sibat.sibat.R.id.navigation_beranda -> {
          supportFragmentManager.beginTransaction()
            .replace(com.hebat.sibat.sibat.R.id.container, BerandaFragment())
            .commit()
          return@setOnNavigationItemSelectedListener true
        }

        com.hebat.sibat.sibat.R.id.navigation_pengaduan -> {
          supportFragmentManager.beginTransaction()
            .replace(com.hebat.sibat.sibat.R.id.container, Pengaduan())
            .commit()
          return@setOnNavigationItemSelectedListener true
        }

        com.hebat.sibat.sibat.R.id.navigation_profil -> {
//          judul.text="Profil"
          if(Prefs.contains("email")){
            supportFragmentManager.beginTransaction()
              .replace(R.id.container,ProfilFragment())
              .commit()
          }else{
//            judul.text="Login"
            supportFragmentManager.beginTransaction()
              .replace(R.id.container,Login())
              .commit()
          }
          return@setOnNavigationItemSelectedListener true
        }

        com.hebat.sibat.sibat.R.id.navigation_lainnya -> {
          supportFragmentManager.beginTransaction()
            .replace(com.hebat.sibat.sibat.R.id.container, LainnyaFragment())
            .commit()
          return@setOnNavigationItemSelectedListener true
        }
      }
      return@setOnNavigationItemSelectedListener false
    }
  }

}
