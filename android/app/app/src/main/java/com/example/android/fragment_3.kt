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

        binding.editText = arguments?.getString("receivedDate")
        binding.editText2 = arguments?.getString("contents")
        binding.editText3 = arguments?.getString("sender")

        return binding.root
    }

    fun changeTextView(receivedDate: String, contents: String, sender: String){
        binding.editText = receivedDate
        binding.editText2 = contents
        binding.editText3 = sender
    }

}