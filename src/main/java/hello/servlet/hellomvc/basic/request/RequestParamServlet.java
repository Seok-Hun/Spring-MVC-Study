package hello.servlet.hellomvc.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;


/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 *
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 2-6. GET URL 쿼리 파라미터 형식과 POST HTML Form 형식이 같은 형식이므로 getParameter()는 둘 다 지원한다.
        System.out.println("[전체 파라미터 조회] - start");
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName+": "+req.getParameter(paramName)));
        //        2-6. 아래 방식도 옛날 방식이지만 가능
        //        Enumeration<String> parameterNames = req.getParameterNames();
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회]");
        // 2-6. 이름이 같은 복수 파라미터가 사용되면 첫 번째 값을 반환한다.
        String username = req.getParameter("username");
        String age = req.getParameter("age");
        System.out.println("username: "+username);
        System.out.println("age: "+age);
        System.out.println();

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        // 2-6. username=hello&username=hello2 같은 형태
        String[] usernames = req.getParameterValues("username");
        for (String name:usernames
             ) {
            System.out.println("username = "+name);
        }

        resp.getWriter().write("ok");
    }
}
