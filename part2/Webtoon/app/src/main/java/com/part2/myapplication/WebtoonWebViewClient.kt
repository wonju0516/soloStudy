package com.part2.myapplication

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.core.view.isVisible

class WebtoonWebViewClient(private val progressBar : ProgressBar): WebViewClient() {

//    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean { //새로운 url로 이동할 때 호출되는 메서드
//        //return true //true를 반환하면 WebView가 아닌 다른 앱에서 처리됨
//        if(request != null && request.url.toString().contains("comic.naver.com")) { // contain -> 특정 문자열이 포함되어 있는지 확인
//            return false //false를 반환하면 WebView에서 처리됨
//        }
//        else return true
//    }

    override fun onPageFinished(view: WebView?, url: String?) { //page loading이 끝났을 때 호출되는 메서드
        super.onPageFinished(view, url)
        // 페이지 로딩이 완료된 후에 실행할 코드 -> progreesbar를 가져오지 못함. -> 그래서 WebViewFragment에서 받아오도록 수정

        progressBar.isVisible = false
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) { //page loading이 시작될 때 호출되는 메서드
        super.onPageStarted(view, url, favicon)

        progressBar.isVisible = true
    }

//    override fun onReceivedError( //페이지 로딩 중 에러가 발생했을 때 호출되는 메서드
//        view: WebView?,
//        request: WebResourceRequest?,
//        error: WebResourceError?
//    ) {
//        super.onReceivedError(view, request, error)
//        // 에러페이지를 띄우는 코드 작성
//    }

}