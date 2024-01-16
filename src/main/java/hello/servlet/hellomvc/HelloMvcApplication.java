package hello.servlet.hellomvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
// 2-2. 스프링에서 서블릿을 사용할 수 있도록 하는 어노테이션 = 서블릿 자동 등록
// 2-2. 스프링이 자동으로 현재 패키지와 하위 패키지를 뒤져 서블릿을 찾아 자동으로 등록하고 실행할 수 있게 한다.
@SpringBootApplication
public class HelloMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloMvcApplication.class, args);
    }

}
