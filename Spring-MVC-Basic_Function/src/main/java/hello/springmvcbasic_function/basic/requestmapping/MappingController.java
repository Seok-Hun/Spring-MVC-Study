package hello.springmvcbasic_function.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"/hello-basic","/hello-go"})
    // 대부분의 속성을 배열로 제공하므로 {"/hello-basic","/hello-go"}와 같이 다중 설정이 가능하다.
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    // 대부분의 속성을 배열로 제공하므로 {"/hello-basic","/hello-go"}와 같이 다중 설정이 가능하다.
    public String mappingGetV1(){
        log.info("mappingGetV1");
        return "ok";
    }

    /**
     * 편리한 축약 어노테이션
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value="/mapping-get-v2")
    public String mappingGetV2(){
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable String userId
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(
            @PathVariable("userId") String data
    ){
        log.info("mappingPath userId={}",data);
        return "ok";
    }

    /**
     * PathVariable 사용 다중
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId){
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode" : 파라미터에 mode가 있어야 한다.
     * params="!mode" : 파라미터에 mode가 없어야 한다.
     * params="mode=debug" : 파라미터 mode 값이 debug여야 한다.
     * params="mode!=debug" : 파라미터 mode 값이 debug가 아니어야 한다.
     * params = {"mode=debug","data=good"} : 파라미터터 mode 값이 debug, 파라미터 data 값이 good 이어야 한다.
     *
     * 해당 컨트롤러 호출 경로: http://localhost:8080/mapping-param?mode=debug
     * ?mode=debug가 없으면 호출되지 않는다.
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode"
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug"
     *
     * 해당 컨트롤러 호출 조건: 요청 Message header에 값이 "debug"인 "mode"가 있어야 한다.
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * HTTP 요청 Content-Type
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     *
     * 해당 컨트롤러 호출 조건: 요청 Message header의 Content-Type이 "application/json" 이어야 한다.
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    // consumes에 문자열로 입력 가능
    // @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * HTTP 요청 Accept
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     *
     * 해당 컨트롤러 호출 조건: 요청 Message header의 Accept가 "text/html" 이어야 한다.
     */
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    // @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}