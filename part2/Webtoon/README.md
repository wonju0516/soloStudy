# 📱 웹툰 앱 만들기 (Part 2 - Chapter 1)

웹뷰(WebView)의 기본기부터 프래그먼트(Fragment) 전환, 그리고 사용자 경험을 위한 로딩바 제어까지 공부한 내용을 정리했습니다.

## 1. 웹뷰(WebView)의 핵심 설정
*   **인터넷 권한**: 웹 페이지를 불러오기 위해 `AndroidManifest.xml`에 권한을 추가했습니다.
*   **자바스크립트 허용**: 웹 페이지 내의 버튼이나 애니메이션이 잘 작동하도록 `settings.javaScriptEnabled = true` 옵션을 줬습니다.
*   **내부 브라우징**: `WebViewClient`를 설정해서 링크를 눌렀을 때 크롬 같은 외부 브라우저로 나가지 않고 앱 안에서 화면이 넘어가도록 처리했습니다.

## 2. 프래그먼트(Fragment) 전환 구조
*   화면을 `WebViewFragment`와 `BFragment`로 쪼개서 관리합니다.
*   **트랜잭션(Transaction)**: `supportFragmentManager`를 사용해 화면을 교체합니다. 
    *   `replace()`로 보여줄 프래그먼트를 갈아 끼우고, 
    *   `commit()`을 호출해야 실제 화면에 반영된다는 "작업 단위" 개념을 익혔습니다.

## 3. 로딩바(ProgressBar) 제어와 커스텀 클라이언트
*   사용자가 "페이지가 로딩 중이구나"라고 느낄 수 있게 `ProgressBar`를 추가했습니다.
*   **WebtoonWebViewClient**: `WebViewClient`를 상속받은 커스텀 클래스를 만들어, 페이지가 시작될 때(`onPageStarted`) 로딩바를 보여주고 다 읽었을 때(`onPageFinished`) 숨기도록 직접 제어했습니다.

## 4. 최신 UI 가이드 (Edge-to-Edge)
*   `enableEdgeToEdge()`를 통해 화면을 상태바 영역까지 꽉 차게 확장했습니다.
*   단순히 넓히기만 하면 UI가 겹칠 수 있으므로, `WindowInsets`를 계산해 시스템 영역만큼 자동으로 패딩을 주는 디테일한 작업도 포함했습니다.

## 5. 뷰 바인딩(ViewBinding) 활용
*   `findViewById`의 번거로움과 에러 위험을 줄이기 위해 바인딩을 사용합니다.
*   **Activity**: `layoutInflater`를 통해 바인딩을 생성.
*   **Fragment**: `onCreateView`에서 인플레이트하고 `binding.root`를 반환하는 구조를 익혔습니다.
