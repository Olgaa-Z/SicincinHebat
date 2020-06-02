package com.hebat.sibat.sibat.ui.ui.config

import com.hebat.sibat.sibat.ui.ui.penduduk.DetailPenduduk

object Config {

    private const val Host="http://192.168.18.16/sibat/" //yang bakal berganti
//    const val url_berita= Host+"index.php/Webservice/select_berita"
////    const val url_gambar= Host+"assets/upload_berita/"
////    const val id="id"
////    const val url_detail_berita= Host+"index.php/Webservice/select_by_get_berita/"
////    const val url_galerifoto= Host+"assets/upload_berita/"
////    val url_login =  Host+"index.php/Login/select/"
////    val DATA_ARRAY = "data"
////    val URL_CARI_BERITA =   "index.php/json/cari_berita?s="

    const val url_pengumuman= Host+"index.php/Webservice/select_pengumuman"
    const val url_detail_pengumuman= Host+"index.php/Webservice/select_by_get_pengumuman/"
    const val url_profilnagari= Host+"index.php/Webservice/select_profilnagari"
    const val url_detail_profilnagari=Host+"index.php/Webservice/select_by_get_profilnagari/"
    const val url_gambar= Host+"assets/upload/"
    const val url_gambar_profil= Host+"assets/upload_info/"
    const val url_gambar_berita= Host+"assets/upload_berita/"
    const val url_gambar_layanan= Host+"assets/upload_layanan/"
    const val url_gambar_potensi= Host+"assets/upload_potensi/"
    const val url_galerifoto= Host+"assets/upload_pengumuman/"
    const val id="id"
    const val id_korong="id_korong"
    val url_layanan = Host+"index.php/Webservice/select_layanan/"
    val url_detail_layanan = Host+"index.php/Webservice/select_by_get_layanan/"
    //    val url_potensi = Host+"index.php/Webservice/select_potensidesa/"
//    val url_detail_potensi = Host+"index.php/Webservice/select_by_get_layanan/"
    val url_detail_berita = Host+"index.php/Webservice/select_by_get_berita/"
    val url_berita = Host+"index.php/Webservice/select_berita/"
    val url_programnagari = Host+"index.php/Webservice/select_programnagari/"
    val url_detail_programnagari = Host+"index.php/Webservice/select_by_get_programnagari/"

    //    val url_penduduk = Host+"index.php/Webservice/selectDataPenduduk/"+DetailPenduduk.id_korong
    val url_penduduk = Host+"index.php/Webservice/selectDataPenduduk/"
    val url_jumlahlembaga = Host+"index.php/Webservice/select_query_lembaga/"
    val url_jumlahprogramdesa = Host+"index.php/Webservice/select_query_programdesa/"
    val url_detail_penduduk = Host+"index.php/Webservice/select_by_get_korong/"
    val url_korong = Host+"index.php/Webservice/select_korong/"
    val url_detail_korong = Host+"index.php/Webservice/selectDataPenduduk/"

    val url_programdesa = Host+"index.php/Webservice/select_programdesa/"
    val url_potensidesa = Host+"index.php/Webservice/select_potensidesa/"
    val  url_detail_potensidesa = Host+"index.php/Webservice/select_by_get_potensidesa/"
    val url_detail_programdesa = Host+"index.php/Webservice/select_by_get_programdesa/"
    val url_galeri = Host+"index.php/Webservice/select_galeri/"
    val url_login =  Host+"index.php/Login/select/"
    val url_lembaga = Host+"index.php/Webservice/select_lembaga/"
    val url_detail_lembaga = Host+"index.php/Webservice/select_by_get_lembaga/"
    val url_pengaduan= Host+"index.php/Webservice/insert_pengaduan"
    val url_datadesawebview= Host+"index.php/Programdesavbiew/"
    val url_programdesawebview= Host+"index.php/Programdesaview/"
    val url_privacywebview= Host+"index.php/Privacy/"
    val url_register = Host+"api/register.php/"
}