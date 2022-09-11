package mk.finki.ukim.emt.productcatalog.services.form;

import lombok.Data;
import mk.finki.ukim.emt.sharedkernel.domain.types.TypesCth;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Money;
import mk.finki.ukim.emt.sharedkernel.domain.sizes.SizesCth;

@Data
public class ProductForm {

    private String productName;
    private String details;
    private TypesCth type;
    private SizesCth size;
    private Money price;
    private int sales;

}
