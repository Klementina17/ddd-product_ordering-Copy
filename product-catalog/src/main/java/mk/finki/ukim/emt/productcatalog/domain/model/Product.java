package mk.finki.ukim.emt.productcatalog.domain.model;

import lombok.Data;
import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.domain.base.AbstractEntity;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Money;
import mk.finki.ukim.emt.sharedkernel.domain.sizes.SizesCth;
import mk.finki.ukim.emt.sharedkernel.domain.types.TypesCth;

import javax.persistence.*;

@Entity
@Table(name="product")
@Getter
public class Product extends AbstractEntity<ProductId> {

    private String productName;

    private String details;

    private TypesCth type;

    private SizesCth size;

    private int sales=0;

    @AttributeOverrides({@AttributeOverride(name ="amount",column = @Column(name="price_amount")),
    @AttributeOverride(name ="currency",column = @Column(name="price_currency"))
    })
    private Money price;


    private Product() {
        super(ProductId.randomId(ProductId.class));
    }

    public static Product build(String productName, String details, TypesCth type, SizesCth size, Money price, int sales) {
        Product p = new Product();
        p.productName = productName;
        p.details=details;
        p.type=type;
        p.size=size;
        p.price = price;
        p.sales= sales;
        return p;
    }

    public void addSales(int qty) {
        this.sales = this.sales - qty;
    }

    public void removeSales(int qty) {
        this.sales -= qty;
    }


}
