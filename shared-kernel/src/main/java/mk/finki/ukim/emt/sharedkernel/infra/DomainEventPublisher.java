package mk.finki.ukim.emt.sharedkernel.infra;

import mk.finki.ukim.emt.sharedkernel.domain.events.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
