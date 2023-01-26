# toyproject-spring_security_ajaxAuthentication

Emal: b635032_@daum.net

## 1. 개선 및 추가내용

toyproject-spring_security v2는 v1에 아래를 반영했다.

+ CSS 개선: 로그인 화면 ui 개선
+ Ajax 인증 추가: 로그인 페이지에서 ajax 인증 기능 추가

## 2. Ajax 인증 아키텍처

![ajaxarchit](https://user-images.githubusercontent.com/62477958/214848948-6bf34a77-217d-474b-bcbf-1cf12edc3baa.png)

+ <b>ajaxFilterChain</b>: 해당 Filter를 추가하여 Form 인증 뿐만 아니라 Ajax 인증도 할 수 있다. 인증 과정은 이전과 전반적으로 유사하다.
+ <b>AuthenticationEntryPoint</b>: 인증되지 않은 사용자가 request를 하면 ExceptionTranslatorFilter에서 처리된다. 이 필터는 EntryPoint을 호출하여 ajax 인증을 하도록 로그인 페이지로 redirection 한다.

ajax 인증을 위해 전송되는 데이터와 이를 처리하는 부분은 login.html, 그리고 AjaxLoginProcessingFilter에서 확인할 수 있다.

## 3. 트러블 슈팅 

### 3.1 Bean 이름 중복 문제

Form 인증과 Ajax 인증에서 동일한 인터페이스를 구현해서 스프링 Bean으로 등록했다. 이에 따라, 빈 이름이 중복되는 문제가 발생했다.

@Bean에 이름을 붙여서 해결할 수 있었다.

### 3.2 보안 설정 클래스 신규 생성

ajax, form 인증 보안 설정 클래스 모두 @Configuration을 통해 관리된다. 아래의 코드를 통해 form 인증 클래스 이전에 ajax 인증을 하도록 필터 구조를 변경했다.

```java
  http.addFilterBefore(ajaxLoginProcessingFilter(), UserPasswordAuthenticationFilter.class);
```

### 3.3 Ajax 인증 AuthenticationManager, AuthenticationProvider 등록

Ajax 인증 전용의 AuthenticationManager와 AuthenticationProvider를 등록하지 않으면, Form 인증에서 사용되는 Manager와 Provider로 연결된다.

이를 해결하기 위해 AjaxLoginProcessingFilter의 부모 추상 클래스인 AbstractAuthenticationProcessingFilter를 분석했다.

활용할 수 있는 코드가 아래와 같이 존재했다.

```java
public abstract class AbstractAuthenticationProcessingFilter extends GenericFilterBean implements ApplicationEventPublisherAware, MessageSourceAware {
  ...
  private AuthenticationManager authenticationManager;
  ...
  public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
  }
  ...
}
```

AuthenticaionManager를 구현한 후, Provider를 set한 후 filter에 설정하여 이 문제를 해결할 수 있었다.
