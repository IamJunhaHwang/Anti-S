package com.example.android

import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.Toast

import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout = TabLayout(this)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()
                when (tab?.text) {
                    "on/off"-> transaction.replace(R.id.tabContent, fragment_1())
                    "report/info"-> transaction.replace(R.id.tabContent, fragment_2())
                    "log"-> transaction.replace(R.id.tabContent, fragment_3())
                    "setting"-> transaction.replace(R.id.tabContent, fragment_4())
                }
                transaction.commit()
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()
                when (tab?.text) {
                    "on/off"-> transaction.replace(R.id.tabContent, fragment_1())
                    "report/info"-> transaction.replace(R.id.tabContent, fragment_2())
                    "log"-> transaction.replace(R.id.tabContent, fragment_3())
                    "setting"-> transaction.replace(R.id.tabContent, fragment_4())
                }
                transaction.commit()
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val transaction = supportFragmentManager.beginTransaction()
                when (tab?.text) {
                    "on/off"-> transaction.replace(R.id.tabContent, fragment_1())
                    "report/info"-> transaction.replace(R.id.tabContent, fragment_2())
                    "log"-> transaction.replace(R.id.tabContent, fragment_3())
                    "setting"-> transaction.replace(R.id.tabContent, fragment_4())
                }
                transaction.commit()
            }
        })
    }

    var initTime = 0L
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean { //앱 종료 기능
        if (keyCode === KeyEvent.KEYCODE_BACK) {
            if(System.currentTimeMillis() - initTime > 3000){
                Toast.makeText(this, "종료하려면 한 번 더 누르세요!!", Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}