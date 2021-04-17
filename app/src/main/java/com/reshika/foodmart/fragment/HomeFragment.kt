package com.reshika.foodmart.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reshika.foodmart.*
import com.reshika.foodmart.adapter.*
import com.reshika.foodmart.api.ServiceBuilder
import com.reshika.foodmart.model.DrinkModel
import com.reshika.foodmart.model.FrozenModel
import com.reshika.foodmart.model.NoodleModel
import com.reshika.foodmart.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {
    //    private var lstpopular = ArrayList<PopularCategoryModel>()
    private lateinit var recycleView: RecyclerView
    private val noodleList= ArrayList<NoodleModel>()

    private lateinit var recycleView_drink: RecyclerView
    private val drinkList= ArrayList<DrinkModel>()

    private lateinit var recycleView_frozen: RecyclerView
    private lateinit var recycleView_fruit: RecyclerView
    private lateinit var recycleView_veg: RecyclerView
    private val frozenList= ArrayList<FrozenModel>()

    private lateinit var cart: TextView
    private lateinit var tvnoodle: TextView
    private lateinit var tvdrink: TextView
    private lateinit var tvfrozen: TextView
    private lateinit var tvfruit: TextView
    private lateinit var tvveg: TextView
    private lateinit var more: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recycleView = view.findViewById(R.id.recycleView)
        tvnoodle= view.findViewById(R.id.tvnoodle)
        tvdrink= view.findViewById(R.id.tvdrink)
        tvfrozen= view.findViewById(R.id.tvfrozen)
        tvfruit= view.findViewById(R.id.tvfruit)
        tvveg= view.findViewById(R.id.tvveg)
        more= view.findViewById(R.id.more)

        recycleView_drink = view.findViewById(R.id.recycleView_drink)
        recycleView_frozen = view.findViewById(R.id.recycleView_frozen)
        recycleView_fruit = view.findViewById(R.id.recycleView_fruit)
        recycleView_veg = view.findViewById(R.id.recycleView_veg)

        more.setOnClickListener {
            loadPopUpMenu()
        }


       ServiceBuilder.noodle=tvnoodle.text.toString()
      loadNoodles()
        ServiceBuilder.drink=tvdrink.text.toString()
        loadDrink()

        ServiceBuilder.frozen=tvfrozen.text.toString()
        loadFrozen()

        ServiceBuilder.fruit=tvfruit.text.toString()
        loadFruit()
        ServiceBuilder.veg=tvveg.text.toString()
        loadVeg()


        return view
    }
    private fun loadPopUpMenu() {
        // Load pop up menu
        val popupMenu = context?.let { PopupMenu(it, more) }
        if (popupMenu != null) {
            popupMenu.menuInflater.inflate(R.menu.bottom_navigation, popupMenu.menu)
        }
        if (popupMenu != null) {
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.AbouttUs -> {
                        startActivity(Intent(context, About_us_Activity::class.java))
                        true
                    }
                    R.id.contactUs -> {
                        startActivity(Intent(context, ContactUs_Activity::class.java))
                        true
                    }
                    R.id.locationLokanthali -> {
                        startActivity(Intent(context, LokanthaliMapsActivity::class.java))
                        true
                    }
                    R.id.locationPutalisadak -> {
                        startActivity(Intent(context, PutalisadakMapsActivity::class.java))
                        true
                    }


                    R.id.rating -> {
                        startActivity(Intent(context, RateUs_ACtivity::class.java))
                        true
                    }
                    R.id.logout ->{
                        startActivity(
                            Intent(
                                context,
                                LoginActivity::class.java
                            )
                        )
                    }
                }
                true
            }
        }
        if (popupMenu != null) {
            popupMenu.show()
        }
    }
//    fun logout() {
//        val preferences = getSharedPreferences("UsernamePasswordPref", Context.MODE_PRIVATE)
//        val editor = preferences.edit()
//        editor.clear()
//        editor.apply()
//        startActivity(
//            Intent(
//                this,
//                LoginActivity::class.java
//            )
//        )
//    }
    private fun loadNoodles() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
               val productRepository=ProductRepository()
                val response =productRepository.getNoodle()
                val lstProduct = response.alldata!!
                Log.d("values","${lstProduct}")
                val lstNoodle = lstProduct[0]
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "$lstNoodle", Toast.LENGTH_LONG).show()
                    recycleView.adapter = NoodleAdapter(lstProduct, requireContext())
                    recycleView.layoutManager =
                            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }



    }


    private fun loadDrink() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productRepository=ProductRepository()
                    val response = productRepository.getDrink()
                    val lstProduct = response.alldata!!
                    withContext(Dispatchers.Main) {

                        recycleView_drink.adapter = lstProduct?.let { DrinkAdapter(it, requireContext()) }
                        recycleView_drink.layoutManager =
                                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }



    }

    private fun loadFrozen() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productRepository=ProductRepository()
                val response = productRepository.getFrozen()
                val lstProduct = response.alldata!!
                withContext(Dispatchers.Main) {

                    recycleView_frozen.adapter = lstProduct?.let { FrozenAdapter(it, requireContext()) }
                    recycleView_frozen.layoutManager =
                            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }



    }
    private fun loadFruit() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productRepository=ProductRepository()
                val response = productRepository.getfruit()
                val lstProduct = response.alldata!!
                withContext(Dispatchers.Main) {

                    recycleView_fruit.adapter = lstProduct?.let { FruitsAdapter(it, requireContext()) }
                    recycleView_fruit.layoutManager =
                            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }



    }
    private fun loadVeg() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val productRepository=ProductRepository()
                val response = productRepository.getVeg()
                val lstProduct = response.alldata!!
                withContext(Dispatchers.Main) {

                    recycleView_veg.adapter = lstProduct?.let { VegAdapter(it, requireContext()) }
                    recycleView_veg.layoutManager =
                            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }



    }



}