package hello.servlet.hellomvc.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name="requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printStartLine(req);
        printHeaders(req);
        printHeaderUtils(req);
    }
    private void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); //GET
        System.out.println("request.getProtocol() = " + request.getProtocol()); //HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); //http
        // http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // /request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        //username=hi
        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure()); //https 사용 유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }
    // 2-3. request 메시지의 start line 정보 출력 메소드

    //Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");
/*
        Enumeration<String> headerNames = request.getHeaderNames();
        // 2-3. 요청 메시지의 헤더 정보들을 리스트로 저장한다.
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            System.out.println(headerName+": "+headerName);
        }
        // 2-3. 헤더 정보 리스트를 순회하며 하나씩 조회한다.
        // 2-3. 헤더 정보를 조회하는 첫번째 방식이다.(옛날 방식)
*/
        request.getHeaderNames().asIterator().forEachRemaining(
                headerName -> System.out.println(headerName+": "+headerName));
        // 2-3. 헤더 정보를 조회하는 두번째 방식이다.(요즘 방식)

//        request.getHeader("host");
        // 2-3. 특정 헤더(host) 정보 검색

        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    //Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " + request.getServerPort()); //Host 헤더
        System.out.println();

        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();

        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " + request.getContentType());
        // 2-3. post 방식이 아니면 body의 타입이 의미가 없으므로 null이다.
        // 2-3. 서블릿에서는 get, post가 따로 없기 때문에 그냥 요청 body가 없으면 null 이다.
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());

        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }

    //기타 정보
    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");

        System.out.println("[Remote 정보]");
        // 2-3. Remote 정보는 요청이 온 것에 대한 정보
        // 2-3. HTTP 메시지에 오는 것은 아니고, 내부에서 네트워크 커넥션이 맺어진 정보들
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost()); //
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr()); //
        System.out.println("request.getRemotePort() = " + request.getRemotePort()); //
        System.out.println();

        System.out.println("[Local 정보]");
        // 2-3. Local 정보는 내 서버에 대한 정보
        System.out.println("request.getLocalName() = " + request.getLocalName()); //
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr()); //
        System.out.println("request.getLocalPort() = " + request.getLocalPort()); //

        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }
}
