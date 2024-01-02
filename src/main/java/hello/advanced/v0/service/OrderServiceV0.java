package hello.advanced.v0.service;

import hello.advanced.v0.repository.OrderRepositoryV0;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {

    private final OrderRepositoryV0 orderRepository;

    /**
     * create
     */
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
