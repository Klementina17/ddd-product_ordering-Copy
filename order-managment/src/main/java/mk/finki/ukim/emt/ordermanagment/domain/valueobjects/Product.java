package mk.finki.ukim.emt.ordermanagment.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.finki.ukim.emt.sharedkernel.domain.base.ValueObject;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Currency;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Money;
import mk.finki.ukim.emt.sharedkernel.domain.sizes.Sizes;
import mk.finki.ukim.emt.sharedkernel.domain.sizes.SizesCth;
import mk.finki.ukim.emt.sharedkernel.domain.types.TypesCth;
import mk.finki.ukim.emt.sharedkernel.domain.types.TypesofClothes;


@Getter
public class Product implements ValueObject {

    private final ProductId id;
    private final String name;
    private final String details;
    private final TypesCth type;
    private final SizesCth size;
    private final Money price;
    private final int sales;

    private Product(){
        this.id=ProductId.randomId(ProductId.class);
        this.name="";
        this.price=Money.valueOf(Currency.MKD,0);
        this.details="";
        this.size=SizesCth.valueOf(Sizes.XS);
        this.type=TypesCth.valueOf(TypesofClothes.Blouses);
        this.sales=0;
    }
    @JsonCreator
    public Product(@JsonProperty("id") ProductId id,
                   @JsonProperty("productName") String name,
                   @JsonProperty("details") String details,
                   @JsonProperty("typesCth") TypesCth type,
                   @JsonProperty("size") SizesCth size,
                   @JsonProperty("price") Money price,
                   @JsonProperty("sales") int sales) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.size = size;
        this.type = type;
        this.price = price;
        this.sales=sales;
    }
}
