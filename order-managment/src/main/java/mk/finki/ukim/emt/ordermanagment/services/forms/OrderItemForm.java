package mk.finki.ukim.emt.ordermanagment.services.forms;

import lombok.Data;
import mk.finki.ukim.emt.ordermanagment.domain.valueobjects.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderItemForm {

    @NotNull
    private Product product;

    @Min(1)
    private int quantity = 1;

}
