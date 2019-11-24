package com.ismealdi.main.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ismealdi.main.R
import com.ismealdi.main.adapter.MenuAdapter
import com.ismealdi.main.adapter.PlaceAdapter
import com.ismealdi.main.databinding.ViewHomeBinding
import com.ismealdi.meepopup.base.AmActivity
import com.ismealdi.meepopup.base.AmApplication
import com.ismealdi.meepopup.schema.Place
import com.ismealdi.meepopup.util.common.Constants
import com.ismealdi.meepopup.util.common.Image
import kotlinx.android.synthetic.main.view_home.*
import com.ismealdi.meepopup.schema.Menu as ModelMenu

class HomeActivity : AmActivity<ViewHomeBinding>(R.layout.view_home) {

    private var menuAdapter: MenuAdapter? = null
    private var placeAdapter: PlaceAdapter? = null

    override fun initView(savedInstanceState: Bundle?) {
        pageLogo(true)

        Image().imageRound(imagePromo, "https://firebasestorage.googleapis.com/v0/b/mee-popup.appspot.com/o/GOPAYPROMO-BLOG-HEADER.jpg?alt=media&token=fa633f17-c785-48c5-9541-61bb52f74777", this)

        initLists()
    }

    private fun initLists() {
        initListMenu()
        initListPlace()
    }

    private fun initListMenu() {
        menuAdapter = MenuAdapter(this)

        listMenu.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        listMenu.adapter = menuAdapter

        val menus = listOf<ModelMenu>(
            ModelMenu("Ayam Goreng", 15000, 4),
            ModelMenu("Ayam Panggang", 25000, 3),
            ModelMenu("Ayam Bawang", 15000, 4),
            ModelMenu("Ayam Panggang", 25000, 3),
            ModelMenu("Ayam Bawang", 15000, 4),
            ModelMenu("Ayam Panggang", 25000, 3),
            ModelMenu("Ayam Bawang", 15000, 4)
        )

        menuAdapter?.submitList(menus)
    }

    private fun initListPlace() {
        placeAdapter = PlaceAdapter(this)

        listPlaces.layoutManager = LinearLayoutManager(this)
        listPlaces.adapter = placeAdapter

        val places = listOf<Place>(
            Place("Man 1 Kota Bogor", "Bogor Barat, Kota Bogor, Jawa Barat 16111"),
            Place("Balai Penelitian Cimanggu", "Bogor Tengah, Kota Bogor, Jawa Barat 16121"),
            Place("Kelurahan Menteng Asri", "Bogor Barat, Kota Bogor, Jawa Barat 16111"),
            Place("RSUD Bogor", "Bogor Selatan, Kota Bogor, Jawa Barat 14500")
        )

        placeAdapter?.submitList(places)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuHistory -> {

            }

            R.id.menuProfile -> {
                AmApplication.amAuth.signOut()
                loadAndLaunchModule(Constants.FEATURE(this).moduleAuth)
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}