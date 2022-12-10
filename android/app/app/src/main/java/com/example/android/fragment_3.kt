package com.example.android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment


class Fragment3 : Fragment() {

    var sender: String = ""
    var contents: String = ""
    var receivedDate: String = ""
    var answer: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_3, container, false)

        //var listview: ListView
        //var adapter: ListViewAdapter

        return view
    }

    fun changeTextView(){
        this.answer = MyApplication.prefs.getString("result", "-")
        this.sender = MyApplication.prefs.getString("sender", "-")
        this.contents =MyApplication.prefs.getString("contents", "-")
        this.receivedDate =MyApplication.prefs.getString("receivedDate", "-")

        Log.d("frag3", answer)
        Log.d("frag3", sender)
        Log.d("frag3", contents)
        Log.d("frag3", receivedDate)
    }

}