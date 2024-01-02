package hello.advanced.config;

import hello.advanced.trace.logTrace.FieldLogTrace;
import hello.advanced.trace.logTrace.LogTrace;
import hello.advanced.trace.logTrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean // 싱글 톤으로 하나만 인스턴스가 등록 된다.
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
