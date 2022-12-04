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
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import kotlinx.android.synthetic.main.fragment_1.*


class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        onoffswitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                switchtext.text="방해금지모드 ON"
                Log.d("모드", "방해금지모드 ON")
            }
            else{
                switchtext.text="방해금지모드 OFF"
                Log.d("모드", "방해금지모드 OFF")
            }
        }

        return inflater.inflate(R.layout.fragment_1, container, false)
    }

}