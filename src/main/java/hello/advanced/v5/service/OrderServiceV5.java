package hello.advanced.v5.service;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logTrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import hello.advanced.v5.repository.OrderRepositoryV5;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;

    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(trace);
    }

    /**
     * create
     */
    public void orderItem(String itemId) {
        template.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
