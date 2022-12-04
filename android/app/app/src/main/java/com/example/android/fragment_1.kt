package com.example.android

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_1.*


class Fragment1 : Fragment() {

    var myReceiver: MyReceiver = MyReceiver()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)
        val statusText: TextView = view.findViewById(R.id.switchtext)
        val switchView: SwitchCompat = view.findViewById(R.id.onoffswitch)

        val intentFilter = IntentFilter()
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED")
        requireActivity().registerReceiver(myReceiver, intentFilter)
        Log.d("onCreate()", "브로드캐스트리시버 등록됨")

        switchView.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                statusText.text="방해금지모드 ON"
                Log.d("모드", "방해금지모드 ON")
                requireActivity().unregisterReceiver(myReceiver)
                Log.d("onDestory()", "브로드캐스트리시버 해제됨")
            }
            else{
                statusText.text="방해금지모드 OFF"
                Log.d("모드", "방해금지모드 OFF")
                requireActivity().registerReceiver(myReceiver, intentFilter)
                Log.d("onCreate()", "브로드캐스트리시버 등록됨")
            }
        }


        return view
    }

}