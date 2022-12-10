package com.example.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.databinding.Fragment3Binding


class Fragment3 : Fragment() {

    private var _binding : Fragment3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment3Binding.inflate(inflater, container, false)

        binding.sender.text = arguments?.getString("sender")
        binding.receivedDate.text = arguments?.getString("receivedDate")
        binding.contents.text = arguments?.getString("contents")

        return binding.root
    }

    fun changeTextView(receivedDate: String, contents: String, sender: String){
        binding.sender.text = sender
        binding.receivedDate.text = receivedDate
        binding.contents.text = contents
    }

}