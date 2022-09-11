package mk.finki.ukim.emt.ordermanagment.service;

import mk.finki.ukim.emt.ordermanagment.domain.exceptions.OrderIdNotExistException;
import mk.finki.ukim.emt.ordermanagment.domain.model.Order;
import mk.finki.ukim.emt.ordermanagment.domain.model.OrderId;
import mk.finki.ukim.emt.ordermanagment.domain.valueobjects.Product;
import mk.finki.ukim.emt.ordermanagment.domain.valueobjects.ProductId;
import mk.finki.ukim.emt.ordermanagment.services.OrderService;
import mk.finki.ukim.emt.ordermanagment.services.forms.OrderForm;
import mk.finki.ukim.emt.ordermanagment.services.forms.OrderItemForm;
import mk.finki.ukim.emt.ordermanagment.xport.client.ProductClient;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Currency;
import mk.finki.ukim.emt.sharedkernel.domain.financial.Money;
import mk.finki.ukim.emt.sharedkernel.domain.sizes.Sizes;
import mk.finki.ukim.emt.sharedkernel.domain.sizes.SizesCth;
import mk.finki.ukim.emt.sharedkernel.domain.types.TypesCth;
import mk.finki.ukim.emt.sharedkernel.domain.types.TypesofClothes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderServiceImplTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductClient productClient;
    private static Product newProduct(String name, Money price) {
        Product p = new Product(ProductId.randomId(ProductId.class), name,"Red", TypesCth.valueOf(TypesofClothes.Blouses),SizesCth.valueOf(Sizes.XS),price,0);
        return p;
    }

    @Test
    public void testPlaceOrderWithRealDate() {
        List<Product> productList = productClient.findAll();
        Product p1 = productList.get(0);
        Product p2 = productList.get(1);

        OrderItemForm oi1 = new OrderItemForm();
        oi1.setProduct(p1);
        oi1.setQuantity(1);

        OrderItemForm oi2 = new OrderItemForm();
        oi2.setProduct(p2);
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setSize(Sizes.XS);
        orderForm.setType(TypesofClothes.Dresses);
        orderForm.setItems(Arrays.asList(oi1,oi2));

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);

        Money outMoney = p1.getPrice().multiply(oi1.getQuantity()).add(p2.getPrice().multiply(oi2.getQuantity()));
        Assertions.assertEquals(newOrder.total(),outMoney);
    }

    }


