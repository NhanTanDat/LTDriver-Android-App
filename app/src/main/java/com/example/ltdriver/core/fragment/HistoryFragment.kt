package com.example.ltdriver.core.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.ltdriver.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.viewpager2.widget.ViewPager2

class HistoryFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPageHistory)

        // Set up ViewPager2 to use FragmentManager and FragmentTransaction directly
        viewPager.adapter = object : androidx.viewpager2.adapter.FragmentStateAdapter(childFragmentManager, lifecycle) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> CompletedFragment()
                    1 -> CancelledFragment()
                    else -> throw IllegalStateException("Invalid position")
                }
            }

            override fun getItemCount(): Int {
                return 2 // Number of tabs
            }
        }

        // Set up TabLayout with ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Hoàn Thành"
                1 -> "Đã Huỷ"
                else -> throw IllegalStateException("Invalid position")
            }
        }.attach()

        return view
    }
}
