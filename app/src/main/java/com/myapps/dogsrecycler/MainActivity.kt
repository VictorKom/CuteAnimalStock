package com.myapps.dogsrecycler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        pager.adapter = SectionsPagerAdapter(supportFragmentManager)
        tabs.setupWithViewPager(pager)
    }

    private inner class SectionsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            val animalFragment = AnimalFragment()
            animalFragment.animal =  when(position){
                0 -> "shibes"
                1 -> "cats"
                2 -> "birds"
                else -> ""
            }
            return animalFragment
        }

        override fun getCount(): Int {
            return 3
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> resources.getString(R.string.dogs_tab)
                1 -> resources.getString(R.string.cats_tabs)
                2 -> resources.getString(R.string.birds_tabs)
                else -> ""
            }
        }
    }
}
