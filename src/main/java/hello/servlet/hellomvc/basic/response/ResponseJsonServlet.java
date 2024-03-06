package hello.servlet.hellomvc.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.hellomvc.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Content-Type: application/json
        resp.setHeader("content-type", "application/json");
        // HTTP 응답으로 HTML을 반환할 때는 application/json으로 지정 필요
        resp.setCharacterEncoding("utf-8");

        HelloData data = new HelloData();
        data.setUsername("kim");
        data.setAge(20);

        //{"username":"kim","age":20}
        String result = objectMapper.writeValueAsString(data);
        resp.getWriter().write(result);
    }
}
