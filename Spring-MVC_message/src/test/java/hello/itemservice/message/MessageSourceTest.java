package hello.itemservice.message;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage() {
        String result = ms.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    /**
     * 메시지가 없는 경우 발생
     */
    @Test
    void notFoundMessageCode(){
        assertThatThrownBy(()->ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    /**
     * 메시지가 없어도 기본 메시지 사용
     */
    @Test
    void notFoundMessageCodeDefaultMessage(){
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    /**
     * 메시지의 매개변수 전달
     */
    @Test
    void argumentMessage(){
        String result = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(result).isEqualTo("안녕 Spring");
    }

    /**
     * locale에 따른 기본 messages 사용
     */
    @Test
    void defaultLang() {
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        // locale 정보가 없으므로 default(messages) 사용
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
        // locale이 있지만, message_ko가 없으므로 default(messages) 사용
    }

    /**
     * locale에 따른 국제화 messages 조회
     */
    @Test
    void enLang() {
        assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
        // locale이 Locale.ENGLISH이므로 messages_en 찾아 사용
    }
}