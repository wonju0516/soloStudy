# Part 2 - 웹툰 앱 (Chapter 1) 📚

본 프로젝트는 안드로이드의 `WebView`를 활용하여 웹 페이지를 앱 내에서 보여주는 기능을 학습하는 단계입니다.

## 🛠 주요 구현 사항

### 1. WebView 설정 및 인터넷 권한 추가
*   **인터넷 권한**: 외부 웹 페이지를 로드하기 위해 `AndroidManifest.xml`에 `<uses-permission android:name="android.permission.INTERNET"/>` 권한을 추가했습니다.
*   **레이아웃 구성**: `activity_main.xml`에서 `ConstraintLayout`을 사용하여 화면 전체를 `WebView`로 꽉 채우도록 구성했습니다.

### 2. ViewBinding 적용
*   `build.gradle.kts`에서 `viewBinding { enable = true }` 설정을 추가했습니다.
*   `MainActivity`에서 기존의 `findViewById` 대신 `ViewBinding`을 사용하여 뷰 객체에 안전하게 접근하도록 코드를 개선했습니다.

### 3. WebView 세부 설정 (MainActivity.kt)
*   **WebViewClient**: `webView.webViewClient = WebViewClient()`를 설정하여, 링크 클릭 시 외부 브라우저(크롬 등)가 아닌 앱 안에서 계속 페이지가 열리도록 구현했습니다.
*   **JavaScript 활성화**: 웹 페이지 내의 동적 기능을 정상적으로 작동시키기 위해 `settings.javaScriptEnabled = true` 옵션을 적용했습니다.
*   **초기 URL 로드**: `webView.loadUrl("https://google.com")`을 통해 앱 실행 시 첫 화면을 지정했습니다.

### 4. 최신 UI 가이드 대응 (Edge-to-Edge)
*   `enableEdgeToEdge()`를 호출하여 화면의 상태바와 네비게이션바 영역까지 뷰를 확장했습니다.
*   `setOnApplyWindowInsetsListener`를 사용하여 시스템 바의 영역만큼 패딩(Padding)을 자동으로 계산해 주어, UI가 겹치지 않도록 처리했습니다.

---

## 💡 학습 포인트
- `WebView` 사용 시 필수적인 권한 설정과 브라우저 동작 제어 방법 습득.
- `ViewBinding`을 통한 효율적인 뷰 참조 방식 이해.
- 안드로이드 시스템 바(System Bars) 대응을 위한 `Insets` 처리 방식 이해.
