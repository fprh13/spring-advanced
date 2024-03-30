package hello.advanced.v2.service;

import hello.advanced.trace.HelloTraceV2;
import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.v2.repository.OrderRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;

    private final HelloTraceV2 trace;
    /**
     * create
     */
    public void orderItem(TraceId traceId, String itemId) {

        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId,"OrderService.orderItem()");
            orderRepository.save(status.getTraceId(),itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw  e; // 예외를 꼭 다시 던져 주어야 한다.
        }
    }
}
