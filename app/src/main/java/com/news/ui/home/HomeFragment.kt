package com.news.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import com.news.NewsApplication
import com.news.R
import com.news.databinding.FragmentHomeBinding
import com.news.ui.news.NewsFragment
import com.news.ui.search.SearchActivity
import com.news.ui.user.UserNavigationView
import com.news.util.showToast

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    //                对应的标题：  头条   国内      国际     娱乐    科技     体育      财经
    private val typeList = listOf("top", "guonei", "guoji", "yule", "keji", "tiyu", "caijing")

    // NewsFragment对象数组
    private val newsFragmentList = typeList.map { NewsFragment.newInstance(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("xx", "onCreateView home")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // 动态加载菜单
        binding.homeToolBar.inflateMenu(R.menu.home_tool_bar_menu)
        binding.homeToolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.refresh -> {
                    val position = binding.newsViewPager.currentItem
                    newsFragmentList[position].viewModel.loadNetWorkData()
                }
            }
            false
        }
        // 设置头像点击事件

        binding.avatar.setOnClickListener {
            UserNavigationView.actionStart(NewsApplication.context)

        }
        // 设置home页面的搜索框不可编辑
        binding.homeEditText.keyListener = null
        // 设置home页面的搜索框的点击事件: 打开SearchActivity这个页面，即搜索页面
        binding.homeEditText.setOnClickListener { SearchActivity.actionStart(NewsApplication.context) }
        // 显示各种类别的NewsFragment
        val viewPager = binding.newsViewPager
        // 设置缓存数量！！！！！ 这里设置为缓存所有页面
        viewPager.offscreenPageLimit = typeList.size
        // 将fragmentList里面的fragment放进viewPager里面，从而渲染到视图上.建议用childFragmentManager
        viewPager.adapter = Adapter(childFragmentManager)
        // 实现viewPager左右滑动与tabLayout这个标签选择器的联动
        binding.newsTabLayout.setupWithViewPager(viewPager)
        Log.e("xx", "onActivityCreated home")
    }

    inner class Adapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int = newsFragmentList.size
        override fun getItem(position: Int): Fragment = newsFragmentList[position]
        override fun getPageTitle(position: Int): CharSequence {
            // 设置标题
            val type = typeList[position]
            return NewsApplication.newsTypeChineseName[type] ?: "未知类型"
        }
    }
}