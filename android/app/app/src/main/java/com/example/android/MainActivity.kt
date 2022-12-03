package com.example.android

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: TabPagerAdapter
    private lateinit var myReceiver: android.content.BroadcastReceiver
    private lateinit var fragment1: Fragment1
    private lateinit var fragment2: Fragment2
    private lateinit var fragment3: Fragment3
    private lateinit var fragment4: Fragment4

    private val MY_PERMISSIONS_REQUEST_SMS_RECEIVED = 99
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

        val intentFilter = IntentFilter(Intent.ACTION_SCREEN_ON)
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF)
        intentFilter.addAction(Intent.ACTION_BOOT_COMPLETED)
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED")

        registerReceiver(myReceiver, intentFilter)
        Log.d("onCreate()", "브로드캐스트리시버 등록됨")

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
            != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECEIVE_SMS)) {
                Log.d("GENE","SMS 모니터링을 위해 권한 필요");
            }
            else {
                Log.d("GENE","SMS 접근 권한 필요");
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), MY_PERMISSIONS_REQUEST_SMS_RECEIVED)
            }
        }
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

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myReceiver)
        Log.d("onDestory()", "브로드캐스트리시버 해제됨")
    }
}

