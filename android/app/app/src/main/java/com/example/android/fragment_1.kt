package com.example.android

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_1.*


class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)
        val statusText: TextView = view.findViewById(R.id.switchtext)
        val switchView: SwitchCompat = view.findViewById(R.id.onoffswitch)

        switchView.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                statusText.text="방해금지모드 ON"
                Log.d("모드", "방해금지모드 ON")
            }
            else{
                statusText.text="방해금지모드 OFF"
                Log.d("모드", "방해금지모드 OFF")
            }
        }


        return view
    }

}