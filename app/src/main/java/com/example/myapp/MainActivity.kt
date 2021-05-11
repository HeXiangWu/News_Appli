package com.example.myapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    val fragmentList = listOf(HomeFragment(), TestFragment("测试1"), TestFragment("测试2"))

    //top(推荐,默认)guonei(国内)guoji(国际)yule(娱乐)tiyu(体育)junshi(军事)keji(科技)caijing(财经)shishang(时尚)youxi(游戏)qiche(汽车)jiankang(健康)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val contentViewPager = findViewById<ViewPager>(R.id.content_view_pager)
        contentViewPager.offscreenPageLimit = fragmentList.size
            contentViewPager.adapter = Myadapter(supportFragmentManager)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> contentViewPager.currentItem = 0
                R.id.nav_test_1 -> contentViewPager.currentItem = 1
                R.id.nav_test_2 -> contentViewPager.currentItem = 2
            }
            false
        }
        contentViewPager.addOnAdapterChangeListener(object : ViewPager.OnPageChangeListener,
            ViewPager.OnAdapterChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                bottomNav.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onAdapterChanged(
                viewPager: ViewPager,
                oldAdapter: PagerAdapter?,
                newAdapter: PagerAdapter?
            ) {
                TODO("Not yet implemented")
            }

        })

    }

    inner class Myadapter(fm: FragmentManager) : FragmentPagerAdapter(
        fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }


    }


}





