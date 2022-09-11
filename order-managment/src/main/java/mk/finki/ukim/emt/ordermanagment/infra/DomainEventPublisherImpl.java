package mk.finki.ukim.emt.ordermanagment.infra;

import lombok.AllArgsConstructor;
import mk.finki.ukim.emt.sharedkernel.domain.events.DomainEvent;
import mk.finki.ukim.emt.sharedkernel.infra.DomainEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DomainEventPublisherImpl implements DomainEventPublisher {

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void publish(DomainEvent event) {
        this.kafkaTemplate.send(event.topic(),event.toJson());

    }
}
