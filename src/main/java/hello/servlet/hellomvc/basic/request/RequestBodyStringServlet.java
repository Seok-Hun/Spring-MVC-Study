package hello.servlet.hellomvc.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // body의 내용을 byte 코드로 가져온다.
        ServletInputStream inputStream = req.getInputStream();

        // 가져온 byte 코드를 String 으로 바꾼다.
        // byte를 문자열로 변환할 때에는 항상 인코딩 정보를 알려주어야 한다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody: "+messageBody);

        resp.getWriter().write("ok");
    }
}
