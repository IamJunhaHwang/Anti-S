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
        val view = inflater.inflate(R.layout.fragment_3, container, false)
        val textmessage: TextView = view.findViewById(R.id.message_text)
        val message: String? = this.arguments?.getString("message")
        textmessage.text = message

        val date = MyReceiver().getDate()
        val content = MyReceiver().getContent()
        val sender = MyReceiver().getSender()

        Log.d("문자 내용1", content)
        Log.d("송신자 번호1", sender)
        Log.d("수신 시간1", date)

        return view
    }

}