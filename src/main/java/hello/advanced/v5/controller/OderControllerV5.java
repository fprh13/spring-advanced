package hello.advanced.v5.controller;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logTrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import hello.advanced.v5.service.OrderServiceV5;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    public OderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId) {
        return template.execute("OrderController.request()", new TraceCallback<>() {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "OK";
            }
        });
    }
}
