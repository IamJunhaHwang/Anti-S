package com.example.android

import android.os.Bundle
import android.util.Log
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
        val view: View = inflater.inflate(R.layout.fragment_3, container, false)
        val textmessage: TextView = view.findViewById(R.id.message_text)
        val message: String? = this.arguments?.getString("message")
        Log.d("문자 내용1", message.toString())
        textmessage.text = message

        return view
    }

}