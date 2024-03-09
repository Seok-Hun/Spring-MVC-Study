package hello.springmvcbasic_function.basic.request;

import hello.springmvcbasic_function.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    // HttpServlet으로 파라미터 받기
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}",username, age);

        response.getWriter().write("ok");
        // ok라는 뷰를 찾는다.
    }

    // @RequestParam으로 파라미터 받기
    @ResponseBody
    // 반환 문자열에 해당하는 뷰를 찾는 것이 아니라 반환 문자열을 응답 메시지에 보낸다.
    // @RestController와 동일하게 처리
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String param1,
            // 아래처럼 작성해도 가능하다.
            // @RequestParam String username
            // String username
            @RequestParam("age") int param2
            // @RequestParam int age
            // int age
    ){
        log.info("username={}, age={}", param1, param2);

        return "ok";
        // @RestController가 아닌 @Controller 이므로 "ok"만 반환하면 ok라는 뷰를 찾는다.
    }

    // @RequestParam의 required 속성 추가
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            // required의 default는 true이다.
            @RequestParam(required = false) Integer age
            // int는 기본형이라 null이 들어갈 수 없다.
            // Integer는 객체형이므로 null이 들어갈 수 있다.
    ){
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    /**
     * @RequestParam - defaultValue 사용
     *
     * 참고: defaultValue는 빈 문자의 경우에도 적용
     * /request-param-default?username=
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age
    ){
        log.info("username={}, age={}", username, age);

        return "ok";
    }

    /**
     * @RequestParam - Map, MultiValueMap
     * Map(key=value)
     * MultiValueMap(key=[value1, value2, ...]) ex) (key=userIds, value=[id1, id2])
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}",
                paramMap.get("username"),
                paramMap.get("age")
        );
        return "ok";
    }

    /**
     * @ModelAttribute 사용
     * 참고: model.addAttribute(helloData) 코드도 함께 자동 적용됨
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        log.info("username={}, age={}",
                helloData.getUsername(),
                helloData.getAge()
        );

        log.info("helloData = {}",helloData);

        return "ok";
    }

    /**
     * @ModelAttribute 생략 가능
     * String, int 같은 단순 타입 = @RequestParam
     * argument resolver 로 지정해둔 타입 외 = @ModelAttribute
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(),
                helloData.getAge());
        return "ok";
    }
}
