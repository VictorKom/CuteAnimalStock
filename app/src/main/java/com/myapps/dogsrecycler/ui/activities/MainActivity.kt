package com.myapps.dogsrecycler.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.myapps.dogsrecycler.R
import com.myapps.dogsrecycler.ui.fragments.AnimalFragment
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

    private inner class SectionsPagerAdapter(fm: FragmentManager)
        : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when(position) {
                0 -> AnimalFragment.newInstance("shibes")
                1 -> AnimalFragment.newInstance("cats")
                2 -> AnimalFragment.newInstance("birds")
                else -> Fragment()
            }
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
