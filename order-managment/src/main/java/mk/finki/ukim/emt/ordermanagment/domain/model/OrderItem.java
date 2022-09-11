package mk.finki.ukim.emt.ordermanagment.domain.model;

import lombok.Getter;
import mk.finki.ukim.emt.ordermanagment.domain.valueobjects.ProductId;
import mk.finki.ukim.emt.sharedkernel.domain.base.AbstractEntity;
import mk.finki.ukim.emt.sharedkernel.domain.base.DomainObjectId;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Money;
import mk.finki.ukim.emt.sharedkernel.domain.sizes.SizesCth;
import mk.finki.ukim.emt.sharedkernel.domain.types.TypesCth;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name="order_item")
@Getter
public class OrderItem extends AbstractEntity<OrderItemId>{

    private Money itemPrice;

    private TypesCth type;

    private SizesCth size;

    @Column(name = "qty",nullable = false)
    private int quantity;

    @AttributeOverride(name="id",column = @Column(name ="product_id",nullable = false))
    private ProductId productId;

    private OrderItem() {
        super(DomainObjectId.randomId(OrderItemId.class));
    }

    public OrderItem(@NonNull ProductId productId, @NonNull Money itemPrice, @NonNull SizesCth size, @NonNull TypesCth type, int qty)
    {
        super(DomainObjectId.randomId(OrderItemId.class));
        this.productId=productId;
        this.itemPrice=itemPrice;
        this.size=size;
        this.type=type;
        this.quantity=qty;
    }
    public Money subtotal()
    {
        return itemPrice.multiply(quantity);
    }

}
