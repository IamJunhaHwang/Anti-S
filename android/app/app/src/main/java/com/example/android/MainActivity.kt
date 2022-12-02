package com.example.android

import android.os.Bundle
import android.view.KeyEvent
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: TabPagerAdapter
    private lateinit var fragment1: Fragment1
    private lateinit var fragment2: Fragment2
    private lateinit var fragment3: Fragment3
    private lateinit var fragment4: Fragment4

    val str = arrayOf("on/off", "report/info", "log", "setting")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager2 = findViewById(R.id.pager)

        adapter = TabPagerAdapter(this)
        viewPager2.setAdapter(adapter)

        TabLayoutMediator(
            tabLayout, viewPager2
        ) { tab, position ->
            val textView = TextView(this@MainActivity)
            textView.text = str[position]
            tab.customView = textView
        }.attach()

        /*tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()
                when (tab?.position) {
                    0-> transaction.replace(R.id.tabContent, fragment1)
                    1-> transaction.replace(R.id.tabContent, fragment2)
                    2-> transaction.replace(R.id.tabContent, fragment3)
                    3-> transaction.replace(R.id.tabContent, fragment4)
                }
                transaction.commit()
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })*/
    }



    var initTime = 0L //앱 종료 기능
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(System.currentTimeMillis() - initTime > 3000){
                Toast.makeText(this, "종료하려면 한 번 더 누르세요!!", Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}