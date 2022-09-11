package mk.finki.ukim.emt.productcatalog.xport.events;

import lombok.AllArgsConstructor;
import mk.finki.ukim.emt.productcatalog.domain.model.ProductId;
import mk.finki.ukim.emt.productcatalog.services.ProductService;
import mk.finki.ukim.emt.sharedkernel.domain.config.TopicHolder;
import mk.finki.ukim.emt.sharedkernel.domain.events.DomainEvent;
import mk.finki.ukim.emt.sharedkernel.domain.events.orders.OrderItemCreated;
import mk.finki.ukim.emt.sharedkernel.domain.events.orders.OrderItemRemoved;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductEventListener {
    private final ProductService productService;

    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_CREATED, groupId = "productCatalog")
    public void consumeOrderItemCreatedEvent(String jsonMessage) {
        try {
            OrderItemCreated event=DomainEvent.fromJson(jsonMessage,OrderItemCreated.class);
            productService.orderItemCreated(ProductId.of(event.getProductId()), event.getQuantity());
        } catch (Exception e){
        }

    }

    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_REMOVED, groupId = "productCatalog")
    public void consumeOrderItemRemovedEvent(String jsonMessage) {
        try {
            OrderItemRemoved event = DomainEvent.fromJson(jsonMessage,OrderItemRemoved.class);
            productService.orderItemRemoved(ProductId.of(event.getProductId()), event.getQuantity());
        } catch (Exception e){

        }

    }

}
