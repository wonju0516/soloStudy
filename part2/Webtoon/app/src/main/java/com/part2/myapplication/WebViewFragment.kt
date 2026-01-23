package com.part2.myapplication

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.part2.myapplication.databinding.FragmentWebviewBinding



class WebViewFragment(private val position : Int, private val webViewUrl : String) : Fragment() {

    var listener: OnTabLayoutNameChanged? = null
    private lateinit var binding: FragmentWebviewBinding
    companion object { // static 역할
        const val SHARED_PREFERENCE_NAME = "WEB_HISTORY"
    }
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
        webView.webViewClient = WebtoonWebViewClient(binding.progressBar, { url ->
            activity?.getSharedPreferences(SHARED_PREFERENCE_NAME , Context.MODE_PRIVATE)?.edit {
                putString("tab$position", url)
            }
        }) //webViewClient 설정 -> WebtoonWebViewClient 클래스 사용 어차피 둘이 상속관계
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(webViewUrl)

        binding.backToLastButton.setOnClickListener {
            val sharedPreference = activity?.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
            val url = sharedPreference?.getString("tab$position", "")
            if( url.isNullOrEmpty()) {
                Toast.makeText(activity, "이전 기록이 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                binding.webView.loadUrl(url)
            }
        }
        binding.changeTabNameButton.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            val editText = EditText(context)

            dialog.setView(editText)
            dialog.setPositiveButton("저장") { //onclicklistener가 와야됨. 그런데 {} ? -> 람다식으로 대체 가능
                _, _ -> //첫번째 매개변수는 다이얼로그, 두번째는 클릭된 버튼의 id
                activity?.getSharedPreferences(SHARED_PREFERENCE_NAME , Context.MODE_PRIVATE)?.edit{
                    putString("tab${position}_name", editText.text.toString())
                    listener?.nameChanged(position, editText.text.toString())
                }

            }
            dialog.setNegativeButton("취소") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            dialog.show()
        }

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

interface OnTabLayoutNameChanged {
    fun nameChanged(position: Int, name: String)
}