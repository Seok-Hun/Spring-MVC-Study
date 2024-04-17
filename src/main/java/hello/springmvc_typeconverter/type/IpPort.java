package hello.springmvc_typeconverter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
// 모든 필드를 사용해 `equals(), hashcode() 생성
@EqualsAndHashCode
public class IpPort {

    private String ip;
    private int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
