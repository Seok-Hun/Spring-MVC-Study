package hello.springmvcbasic_function.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

    // private final Logger log = LoggerFactory.getLogger(LogTestController.class);
    // LogTestController.class 자리에 getClass()도 가능
    // @Slf4j 사용시 생략 가능

    @GetMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        log.trace(" trace log={}",name);
        log.debug(" debug log={}",name);
        log.info(" info log={}",name);
        log.warn(" warn log={}",name);
        log.error(" error log={}",name);

        return "ok";
    }
}
