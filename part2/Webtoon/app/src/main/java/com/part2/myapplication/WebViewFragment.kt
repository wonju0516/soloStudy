package com.part2.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.part2.myapplication.databinding.FragmentWebviewBinding



class WebViewFragment : Fragment() {
    private lateinit var binding: FragmentWebviewBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater)
        return binding.root // root -> fragment_webview.xml의 최상위 뷰 (constraintLayout) -> 전체반환
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView = binding.webView
        webView.webViewClient = WebtoonWebViewClient(binding.progressBar) //webViewClient 설정 -> WebtoonWebViewClient 클래스 사용 어차피 둘이 상속관계
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://comic.naver.com/")

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object:
            OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if(webView.canGoBack()){ //webView가 뒤로갈 수 있는지 확인
                    webView.goBack() //뒤로가기
                } else {
                    isEnabled = false //기능 비활성화
                    requireActivity().onBackPressedDispatcher.onBackPressed() // 기능 -> Activity에서 처리 -> 앱 종료가 됨
                }
            }
        })
    }
}