package hello.servlet.hellomvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.hellomvc.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {
    // 스프링 부트는 기본 json 라이브러리로 jackson을 사용한다.
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        // json 문자열을 HelloData 객체로 변환(기본적으론 json도 문자열이긴 하다.)
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("helloData.username = "+helloData.getUsername());
        System.out.println("helloData.age = "+helloData.getAge());

        resp.getWriter().write("ok");
    }
}
