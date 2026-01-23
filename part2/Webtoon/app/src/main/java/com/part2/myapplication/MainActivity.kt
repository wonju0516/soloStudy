package com.part2.myapplication

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.part2.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnTabLayoutNameChanged {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val sharedPreference = getSharedPreferences(WebViewFragment.Companion.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val tab0 = sharedPreference?.getString("tab0_name", "웹툰1")
        val tab1 = sharedPreference?.getString("tab1_name", "웹툰2")
        val tab2 = sharedPreference?.getString("tab2_name", "웹툰3")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
//                val textView = TextView(this@MainActivity) // this@MainActivity -> MainActivity의 context를 의미
//                textView.text = when(position) {
//                    0 -> tab0
//                    1 -> tab1
//                    else -> tab2
//                }
//                textView.text = "position $position"
//                textView.gravity = Gravity.CENTER
//
//                tab.customView = textView
                tab.text = when(position) {
                    0 -> tab0
                    1 -> tab1
                    else -> tab2
                }
            }
        }.attach() // attach -> tabLayout과 viewPager 연결

    }

    override fun nameChanged(position: Int, name: String) {
        val tab = binding.tabLayout.getTabAt(position)
        tab?.text = name

    }

}