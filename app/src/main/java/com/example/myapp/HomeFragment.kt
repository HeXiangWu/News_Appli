package com.example.myapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class HomeFragment:Fragment() {

    private val titleList = listOf(
        "国内",
        "国际",
        "娱乐",
        "体育",
        "军事"
    )
    private val fragmentList = ArrayList<NewsFragment>()
    private val newsTypeList = listOf(
        "guonei",
        "guoji",
        "yule",
        "tiyu",
        "junshi"
    )
    private lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var toolbar: Toolbar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        tabLayout = view.findViewById(R.id.news_tab_layout)
        viewPager = view.findViewById(R.id.news_view_pager)
        toolbar = view.findViewById(R.id.home_tool_bar)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        toolbar.inflateMenu(R.menu.home_tool_bar_menu)

        for (newsType in newsTypeList) {
            fragmentList.add(NewsFragment(newsType))
        }
        viewPager.adapter = Myadapter(childFragmentManager)
        viewPager.offscreenPageLimit = titleList.size
        tabLayout.setupWithViewPager(viewPager)
    }
    inner class Myadapter(fm: FragmentManager) : FragmentPagerAdapter(fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }

    }

}