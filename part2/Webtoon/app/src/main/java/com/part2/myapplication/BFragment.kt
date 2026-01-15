package com.part2.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.part2.myapplication.databinding.FragmentSecondBinding
import com.part2.myapplication.databinding.FragmentWebviewBinding

class BFragment : Fragment() {
    private lateinit var binding : FragmentSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater)
        return binding.root // root -> fragment_webview.xml의 최상위 뷰 (constraintLayout) -> 전체반환
    }
}