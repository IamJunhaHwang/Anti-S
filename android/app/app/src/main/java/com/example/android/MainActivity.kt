package com.example.android

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
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
import com.example.android.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_1.*
import kotlinx.android.synthetic.main.fragment_3.*


open class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: TabPagerAdapter
    private lateinit var fragment1: Fragment1
    private lateinit var fragment2: Fragment2
    val fragment3 = Fragment3()
    private lateinit var fragment4: Fragment4
    lateinit var binding: ActivityMainBinding

    val str = arrayOf("on/off", "report/info", "log", "setting")

    @SuppressLint("MissingInflatedId")
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

        requirePerms()
        Log.d("권한 요청", "권한 요청 진행함")

        intent = getIntent()

        val sender = intent?.getStringExtra("sender").toString()
        val contents = intent?.getStringExtra("contents").toString()
        val receivedDate = intent?.getStringExtra("receivedDate").toString()

        val bundle = Bundle()
        bundle.putString("sender", sender)
        bundle.putString("contents", contents)
        bundle.putString("receivedDate", receivedDate)

        fragment3.arguments = bundle
        if(bundle != null) {
            processedIntent(intent) //MyReceiver에서 SMS 정보 받아오기
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        processedIntent(intent)
    }

    private fun requirePerms() {
        val permissions = arrayOf<String>(Manifest.permission.RECEIVE_SMS)
        val permissionCheck =
            ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, permissions, 1)
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
            Log.d("앱 종료 버튼", "앱이 종료되었음")
        }
        return super.onKeyDown(keyCode, event)
    }

    fun processedIntent(intent: Intent?) {
        val sender = intent?.getStringExtra("sender").toString()
        val contents = intent?.getStringExtra("contents").toString()
        val receivedDate = intent?.getStringExtra("receivedDate").toString()

        fragment3.changeTextView(sender, contents, receivedDate)

    }


    /*override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myReceiver)
        Log.d("onDestory()", "브로드캐스트리시버 해제됨")
    }*/
}
