package hello.springmvc_typeconverter;

import hello.springmvc_typeconverter.converter.IntegerToStringConverter;
import hello.springmvc_typeconverter.converter.IpPortToStringConverter;
import hello.springmvc_typeconverter.converter.StringToIntegerConverter;
import hello.springmvc_typeconverter.converter.StringToIpPortConverter;
import hello.springmvc_typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 컨버터가 포맷터보다 우선순위
        //registry.addConverter(new StringToIntegerConverter());
        //registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        // 포맷터 적용
        registry.addFormatter(new MyNumberFormatter());
    }
}
