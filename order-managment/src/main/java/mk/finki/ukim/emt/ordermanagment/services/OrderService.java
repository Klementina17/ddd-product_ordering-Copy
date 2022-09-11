package mk.finki.ukim.emt.ordermanagment.services;

import mk.finki.ukim.emt.ordermanagment.domain.exceptions.OrderIdNotExistException;
import mk.finki.ukim.emt.ordermanagment.domain.exceptions.OrderItemIdNotExistException;
import mk.finki.ukim.emt.ordermanagment.domain.model.Order;
import mk.finki.ukim.emt.ordermanagment.domain.model.OrderId;
import mk.finki.ukim.emt.ordermanagment.domain.model.OrderItemId;
import mk.finki.ukim.emt.ordermanagment.services.forms.OrderForm;
import mk.finki.ukim.emt.ordermanagment.services.forms.OrderItemForm;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderId placeOrder(OrderForm orderForm);

    List<Order> findAll();

    Optional<Order> findById(OrderId id);

    void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotExistException;

    void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdNotExistException, OrderItemIdNotExistException;



}
