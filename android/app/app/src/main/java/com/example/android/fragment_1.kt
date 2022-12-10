package com.example.android

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment


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