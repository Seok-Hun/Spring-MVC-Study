package hello.servlet.hellomvc.web.frontcontroller.v3;

import hello.servlet.hellomvc.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
    // v1, v2와 달리 HttpServlet 관련 파라미터가 없다.
    // 즉, 서블릿에 종속적이지 않다.
}
