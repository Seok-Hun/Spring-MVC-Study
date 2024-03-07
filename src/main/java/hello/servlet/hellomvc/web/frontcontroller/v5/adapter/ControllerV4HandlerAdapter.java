package hello.servlet.hellomvc.web.frontcontroller.v5.adapter;

import hello.servlet.hellomvc.web.frontcontroller.ModelView;
import hello.servlet.hellomvc.web.frontcontroller.v3.ControllerV3;
import hello.servlet.hellomvc.web.frontcontroller.v4.ControllerV4;
import hello.servlet.hellomvc.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = createParamMap(req);
        HashMap<String, Object> model = new HashMap<>();
        // HashMap은 Key, Value 값으로 null을 허용한다.

        String viewName = controller.process(paramMap, model);

        ModelView modelView = new ModelView(viewName);
        modelView.setModel(model);
        // v4는 v3와는 다르게 modelView를 사용하지 않는다.
        // 따라서 어댑터에서 modelView를 사용하는 방식으로 변경해준다.

        return modelView;
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String,String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator().
                forEachRemaining(paramName ->
                        paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
