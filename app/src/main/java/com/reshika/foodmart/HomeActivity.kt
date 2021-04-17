package com.reshika.foodmart

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.reshika.foodmart.adapter.*
import com.reshika.foodmart.fragment.CartFragment
import com.reshika.foodmart.fragment.HomeFragment
import com.reshika.foodmart.fragment.OrderFragment
import com.reshika.foodmart.fragment.ProfileFragment
import com.reshika.foodmart.model.DrinkModel
import com.reshika.foodmart.model.FrozenModel
import com.reshika.foodmart.model.NoodleModel

class HomeActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager;
    private lateinit var tabLayout: TabLayout;
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        loadViewPagerAdapter();
    }
    private fun loadViewPagerAdapter() {

        val adapter = ViewPageAdapter(supportFragmentManager);
        adapter.addFragment(HomeFragment(), "Home");
        adapter.addFragment(CartFragment(), "Cart");
        adapter.addFragment(OrderFragment(), "Order");
        adapter.addFragment(ProfileFragment(),"Profile");
        viewPager.adapter = adapter;
        tabLayout.setupWithViewPager(viewPager);

//        tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_home_24);
//        tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_person_24);
//        tabLayout.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_notifications_24);


    }


}