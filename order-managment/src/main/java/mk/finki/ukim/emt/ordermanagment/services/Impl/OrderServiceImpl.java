package mk.finki.ukim.emt.ordermanagment.services.Impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.emt.ordermanagment.domain.exceptions.OrderIdNotExistException;
import mk.finki.ukim.emt.ordermanagment.domain.exceptions.OrderItemIdNotExistException;
import mk.finki.ukim.emt.ordermanagment.domain.model.Order;
import mk.finki.ukim.emt.ordermanagment.domain.model.OrderId;
import mk.finki.ukim.emt.ordermanagment.domain.model.OrderItemId;
import mk.finki.ukim.emt.ordermanagment.domain.repository.OrderRepository;
import mk.finki.ukim.emt.ordermanagment.services.OrderService;
import mk.finki.ukim.emt.ordermanagment.services.forms.OrderForm;
import mk.finki.ukim.emt.ordermanagment.services.forms.OrderItemForm;
import mk.finki.ukim.emt.sharedkernel.domain.events.orders.OrderItemCreated;
import mk.finki.ukim.emt.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Validator validator;


    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm,"order must not be null.");
        var constraintViolations = validator.validate(orderForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        newOrder.getOrderItemsList().forEach(item->domainEventPublisher.publish(new OrderItemCreated(item.getProductId().getId(),item.getQuantity())));
        return newOrder.getId();

    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderRepository.findById(id);
    }

    @Override
    public void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.addItem(orderItemForm.getProduct(),orderItemForm.getQuantity());
        orderRepository.saveAndFlush(order);
        domainEventPublisher.publish(new OrderItemCreated(orderItemForm.getProduct().getId().getId(),orderItemForm.getQuantity()));
    }

    @Override
    public void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdNotExistException, OrderItemIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.removeItem(orderItemId);
        orderRepository.saveAndFlush(order);
    }

    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(Instant.now(),orderForm.getCurrency(),orderForm.getSize(),orderForm.getType());
        orderForm.getItems().forEach(item->order.addItem(item.getProduct(),item.getQuantity()));
        return order;
    }

}
