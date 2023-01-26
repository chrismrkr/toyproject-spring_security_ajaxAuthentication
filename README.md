# toyproject-spring_security_ajaxAuthentication

Emal: b635032_@daum.net

## 1. 개선 및 추가내용

toyproject-spring_security v2는 v1에 아래를 반영했다.

+ CSS 개선: 로그인 화면 ui 개선
+ Ajax 인증 추가: 로그인 페이지에서 ajax 인증 기능 추가

## 2. Ajax 인증 아키텍처

![ajaxarchit](https://user-images.githubusercontent.com/62477958/214848948-6bf34a77-217d-474b-bcbf-1cf12edc3baa.png)

+ ajaxFilterChain: 해당 Filter를 추가하여 Form 인증 뿐만 아니라 Ajax 인증도 할 수 있다. 인증 과정은 이전과 전반적으로 유사하다.
+ AuthenticationEntryPoint: 인증되지 않은 사용자가 request를 하면 ExceptionTranslatorFilter에서 처리된다. 이 필터는 EntryPoint을 호출하여 ajax 인증을 하도록 로그인 페이지로 redirection 한다.

## 3. 트러블 슈팅 
