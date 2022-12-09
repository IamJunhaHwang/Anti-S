package com.example.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Fragment3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_3, container, false)

        val editText: TextView = view.findViewById(R.id.sendNum);
        val editText2: TextView = view.findViewById(R.id.content);
        val editText3: TextView = view.findViewById(R.id.time);

        return view
    }

}