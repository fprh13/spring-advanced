package hello.advanced.v2.controller;

import hello.advanced.trace.HelloTraceV1;
import hello.advanced.trace.HelloTraceV2;
import hello.advanced.trace.TraceStatus;
import hello.advanced.v2.service.OrderServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(status.getTraceId(),itemId);
            trace.end(status);
            return "OK";
        } catch (Exception e) {
            trace.exception(status, e);
            throw  e; // 예외를 꼭 다시 던져 주어야 한다.
        }
    }
}
