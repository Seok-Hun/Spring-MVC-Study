package hello.servlet.hellomvc.web.frontcontroller.v1;

import hello.servlet.hellomvc.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.hellomvc.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.hellomvc.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
// urlPatterns에 *을 작성한 이유는 v1하위에 어떤 경로가 들어와도 Front Controller가 우선적으로 호출되도록 하기 위해서이다.
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();
    // key: URL, value: Controller
    // 어떤 URL이 호출되든 ControllerV1로 호출하도록 한다.


    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }
    // 해당 서블릿이 생성될 때 controllerMap에 각각의 경로와 컨트롤러를 넣는다.

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = req.getRequestURI();
        // 요청의 URI 저장

        ControllerV1 controller = controllerMap.get(requestURI);
        if(controller==null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(req,resp);
        // 인터페이스로 controller를 구현하여 다형성을 확보할 수 있다.
    }
}
