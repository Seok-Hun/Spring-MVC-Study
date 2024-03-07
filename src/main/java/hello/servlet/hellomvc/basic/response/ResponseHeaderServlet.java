package hello.servlet.hellomvc.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // [status-line 작성 (HTTP 스펙의 응답 첫번째 줄)]
        resp.setStatus(HttpServletResponse.SC_OK);
        // HTTP 응답 코드 지정
        // 직접 응답 코드 숫자를 작성해도 되지만, HttpServletResponse 안에 응답 코드들이 상수로 저장되어 있다.

        // [Header 데이터 세팅]
        resp.setHeader("Content-Type","text/plain;charset=utf-8");
        // 인코딩 정보를 추가해야 한글이 깨지지 않음. 인코딩 정보가 없으면 임의로 인코딩 정보를 정한다.
        resp.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        // 캐시 정보 추가: 캐시를 완전히 무효화시킴
        resp.setHeader("Pragma","no-cache");
        // 과거 버전까지 캐시를 없앰
        resp.setHeader("my-header","hello");
        // my-header라는 임의의 헤더 추가

        // [Header 편의 기능 메서드]
        content(resp);
        // 함수를 이용한 헤더 데이터 추가
        cookie(resp);
        // 쿠키 생성
        redirect(resp);
        // Redirect 설정

        // [message body 작성]
        PrintWriter writer = resp.getWriter();
        writer.println("ok");
    }

    private void content(HttpServletResponse resp){
        /*
        [Header 내용]
        Content-Type: text/plain;charset=utf-8
        Content-Length: 2
        */

        // resp.setHeader("Content-Type", "text/plain;charset=utf-8");
        // 이렇게 작성해도 되지만 아래와 같이 편리하게 작성할 수도 있다.
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");

        resp.setContentLength(2); // 생략 시 content 길이에 맞춰 자동 생성
    }

    private void cookie(HttpServletResponse resp){
        /*
        [Cookie 내용]
        Set-Cookie: myCookie=good; Max-Age=600;
         */

        // resp.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        // 이렇게 작성해도 되지만 아래와 같이 편리하게 작성할 수도 있다.
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); // 유효 시간 600 초
        resp.addCookie(cookie);
    }

    private void redirect(HttpServletResponse resp) throws IOException {
        /*
        [Redirect 설정]
        Status Code: 302
        Location: /basic/hello-form.html
        Location 경로로 클라이언트를 redirect 시킨다.
         */

        /*
        resp.setStatus(HttpServletResponse.SC_FOUND); // 302
        resp.setHeader("Location", "/basic/hello-form.html");
        이렇게 작성해도 되지만 아래와 같이 편리하게 작성할 수도 있다.
        */
        resp.sendRedirect("/basic/hello-form.html");
    }
}
