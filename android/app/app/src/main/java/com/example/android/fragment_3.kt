package com.example.android

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class Fragment3 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)

        val passedIntent = getIntent()
        processCommand(passedIntent)

        return view
    }

    private fun processCommand(intent: Intent?) {
        if (intent != null) {
            val sender = intent.getStringExtra("sender")
            val contents = intent.getStringExtra("contents")
            val receivedDate = intent.getStringExtra("receivedDate")
            editText.setText(sender)
            editText3.setText(contents)
            editText2.setText(receivedDate)
        }
    }


    protected fun onNewIntent(intent: Intent?) {
        processCommand(intent)
        super.onNewIntent(intent)
    }

}