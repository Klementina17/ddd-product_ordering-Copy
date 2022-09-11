package mk.finki.ukim.emt.productcatalog.domain.valueobjects;

import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Quantity implements ValueObject {
    private final int quantity;

    protected Quantity()
    {
        this.quantity=0;
    }

}
