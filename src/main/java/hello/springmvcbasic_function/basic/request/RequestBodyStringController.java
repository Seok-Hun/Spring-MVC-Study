package hello.springmvcbasic_function.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
public class RequestBodyStringController {

    // 요청 message body의 문자열 처리
    @RequestMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String s = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}",s);

        response.getWriter().write("ok");
    }

    /**
     * InputStream(Reader): HTTP 요청 메시지 바디의 내용을 직접 조회
     * OutputStream(Writer): HTTP 응답 메시지의 바디에 직접 결과 출력
     */
    @RequestMapping("/request-body-string-v2")
    public void requestBodyStringV2(
            InputStream inputStream,
            Writer responseWriter
    ) throws IOException {
        String s = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}",s);

        responseWriter.write("ok");
    }

    /**
     * HttpEntity: HTTP header, body 정보를 편리하게 조회
     * - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     *
     * 응답에서도 HttpEntity 사용 가능
     * - 메시지 바디 정보 직접 반환(view 조회X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     */
    @RequestMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
        String body = httpEntity.getBody();

        // HttpHeaders headers = httpEntity.getHeaders();
        // 헤더 정보도 가져올 수 있다.
        log.info("messageBody={}",body);

        return new HttpEntity<>("ok");
    }

    // HttpEntity 상속 : RequestEntity, ResponseEntity
    @RequestMapping("/request-body-string-v3.2")
    public HttpEntity<String> requestBodyStringV32(RequestEntity<String> httpEntity) {
        String body = httpEntity.getBody();

        // HttpHeaders headers = httpEntity.getHeaders();
        // 헤더 정보도 가져올 수 있다.
        log.info("messageBody={}",body);

        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

    /**
    * @RequestBody
    * - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
    * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
    *
    * @ResponseBody
    * - 메시지 바디 정보 직접 반환(view 조회X)
    * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용 */
    @ResponseBody
    @RequestMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {

        log.info("messageBody={}",messageBody);

        return "OK";
    }
}
