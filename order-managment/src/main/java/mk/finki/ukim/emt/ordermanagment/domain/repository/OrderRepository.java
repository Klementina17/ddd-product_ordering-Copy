package mk.finki.ukim.emt.ordermanagment.domain.repository;

import mk.finki.ukim.emt.ordermanagment.domain.model.Order;
import mk.finki.ukim.emt.ordermanagment.domain.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderId> {
}
