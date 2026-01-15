# 📱 웹툰 앱 만들기 (Part 2 - Chapter 1)

단순한 웹뷰 앱을 넘어, 프래그먼트 구조를 활용해 화면을 전환하고 다루는 방법을 학습했습니다.

## ✅ 주요 구현 내용

### 1. 프래그먼트(Fragment) 기반 화면 전환
*   `MainActivity` 하나에서 다 처리하지 않고, `WebViewFragment`와 `BFragment`로 기능을 쪼개서 관리하도록 만들었습니다.
*   `supportFragmentManager`의 `beginTransaction`을 사용해, 사용자가 버튼을 누를 때마다 `FragmentContainerView`의 내용을 동적으로 교체(`replace`)합니다. 
*   코드 주석에도 달아두었듯, 트랜잭션(`transaction`)의 시작과 끝(`commit`) 개념을 익혔습니다.

### 2. 웹뷰(WebView) 심화 설정
*   **앱 안에서 페이지 이동**: 링크 클릭 시 외부 브라우저로 튕기지 않고 앱 내부에서 계속 탐색할 수 있게 `WebViewClient`를 적용했습니다.
*   **자바스크립트 허용**: 웹 페이지가 정상적으로 동작하도록 `settings.javaScriptEnabled = true` 옵션을 줬습니다.
*   **인터넷 권한**: `AndroidManifest.xml`에 인터넷 권한을 추가해 웹 페이지 로딩이 가능하게 설정했습니다.

### 3. 최신 UI 가이드 (Edge-to-Edge) 적용
*   `enableEdgeToEdge()`를 사용해 상태바 영역까지 앱 화면이 꽉 차게 만들었습니다.
*   화면이 잘리지 않게 `WindowInsets`를 계산해서 시스템 바 영역만큼 자동으로 패딩이 들어가도록 처리했습니다.

### 4. 뷰 바인딩(ViewBinding) 활용
*   `findViewById`를 일일이 쓰지 않고 바인딩 객체를 통해 뷰에 안전하게 접근합니다. `Activity`와 `Fragment` 각각의 바인딩 처리 방식을 익혔습니다.
