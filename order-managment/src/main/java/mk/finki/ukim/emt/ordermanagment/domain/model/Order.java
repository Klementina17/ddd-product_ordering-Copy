package mk.finki.ukim.emt.ordermanagment.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.finki.ukim.emt.ordermanagment.domain.valueobjects.Product;
import mk.finki.ukim.emt.sharedkernel.domain.base.AbstractEntity;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Currency;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Money;
import mk.finki.ukim.emt.sharedkernel.domain.sizes.Sizes;
import mk.finki.ukim.emt.sharedkernel.domain.types.TypesofClothes;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="orders")
@Getter
public class Order extends AbstractEntity<OrderId> {

    private Instant orderOn;

    @Column(name="order_size")
    @Enumerated(value = EnumType.STRING)
    private Sizes size;

    @Column(name="order_type")
    @Enumerated(value = EnumType.STRING)
    private TypesofClothes type;

    @Enumerated(value = EnumType.STRING)
    private OrderState orderState;

    @Column(name="order_currency")
    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<OrderItem> orderItemsList = new HashSet<>() ;

    private Order() {
        super(OrderId.randomId(OrderId.class));
    }

    public Order(Instant now, Currency currency, Sizes size, TypesofClothes type) {
        super(OrderId.randomId(OrderId.class));
        this.orderOn=now;
        this.currency=currency;
        this.size=size;
        this.type=type;
    }

    public Money total()
    {
      return orderItemsList.stream().map(OrderItem::subtotal).reduce(new Money(currency,0),Money::add);
    }

    public OrderItem addItem(@NotNull Product product, int qty)
    {
        Objects.requireNonNull(product,"product must not be null");
        var item = new OrderItem(product.getId(),product.getPrice(),product.getSize(),product.getType(),qty);
        orderItemsList.add(item);
        return item;
    }

    public void removeItem(@NonNull OrderItemId orderItemId)
    {
        Objects.requireNonNull(orderItemId,"Order Item must not be null");
        orderItemsList.removeIf(v->v.getId().equals(orderItemId));
    }
}
