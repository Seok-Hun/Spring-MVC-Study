package hello.servlet.hellomvc.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
// 2-2. name은 아무거나 상관 없다.
// 2-2. urlPatterns(hello)로 접속하면 실행한다는 의미
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.service(req, resp);
        // 2-2. 해당 코드가 있으면 405 에러(HTTP 메서드가 요청된 리소스에 대해 허용되지 않음)가 뜬다.
        // 2-2. service() 메서드를 재정의하여 HTTP 요청을 할 때 기본적으로 정의된 HTTP 메서드 처리가 잘못될 수 있다.
        System.out.println("HelloServlet.service");
        System.out.println("req = " + req);
        System.out.println("resp = " + resp);

        String username = req.getParameter("username");
        // 2-2. getParameter로 쿼리 스트링 값을 구할 수 있음
        System.out.println("username = " + username);

        resp.setContentType("text/plain");
        // 2-2. 응답 데이터의 형식
        resp.setCharacterEncoding("utf-8");
        // 2-2. 응답 문자 세트 인코딩 정보
        // 2-2. 위 두 값은 응답 Header에 들어간다.
        resp.getWriter().write("hello"+username);
        // 2-2. getWriter().write()는 응답 HTTP Message Body에 데이터가 들어간다.
    }
    // 2-2. req, resp는 스프링에서 서블릿으로 전달하는 요청, 응답 객체이다.
    // 2-2. 서블릿이 호출되면 해당 메서드가 호출됨
    // 2-2. Ctrl+o : Override/Implement 메소드 선택해 추가 가능
}
