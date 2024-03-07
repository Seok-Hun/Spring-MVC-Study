package hello.servlet.hellomvc.web.frontcontroller.v5.adapter;

import hello.servlet.hellomvc.web.frontcontroller.ModelView;
import hello.servlet.hellomvc.web.frontcontroller.v3.ControllerV3;
import hello.servlet.hellomvc.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3); // handler가 ControllerV3의 구현체이면 true
    }

    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler; // supports()에서 ControllerV3만 확인했으므로 직접 ControllerV3로 지정해 주어도 된다.

        Map<String, String> paramMap = createParamMap(req);
        return controller.process(paramMap);
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String,String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator().
                forEachRemaining(paramName ->
                        paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
