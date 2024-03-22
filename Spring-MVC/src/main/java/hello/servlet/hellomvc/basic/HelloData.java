package hello.servlet.hellomvc.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
// lombok을 이용한 자바 프로퍼티 작업법
public class HelloData {
    private String username;
    private int age;
}
