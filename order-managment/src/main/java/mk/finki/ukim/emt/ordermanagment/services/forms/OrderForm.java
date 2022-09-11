package mk.finki.ukim.emt.ordermanagment.services.forms;

import lombok.Data;

import mk.finki.ukim.emt.ordermanagment.domain.model.OrderState;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Currency;
import mk.finki.ukim.emt.sharedkernel.domain.sizes.Sizes;
import mk.finki.ukim.emt.sharedkernel.domain.types.TypesofClothes;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
public class OrderForm {

    @NotNull
    private Currency currency;

    @NotNull
    private Sizes size;

    @NotNull
    private TypesofClothes type;

    @Valid
    @NotEmpty
    private List<OrderItemForm> items = new ArrayList<>();

}
