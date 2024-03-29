package hello.advanced.v3.service;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logTrace.LogTrace;
import hello.advanced.v3.repository.OrderRepositoryV3;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;

    private final LogTrace trace;
    /**
     * create
     */
    public void orderItem(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw  e; // 예외를 꼭 다시 던져 주어야 한다.
        }
    }
}
