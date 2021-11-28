package ru.cherry.springhomework.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.cherry.springhomework.dao.DocumentRepository;

@Component
@RequiredArgsConstructor
public class DocflowIndicator implements HealthIndicator {
    private final DocumentRepository documentRepository;

    @Override
    public Health health() {
        if (isDocumentsLoaded()) {
            return Health.up()
                    .withDetail("message", "Документы загружены.")
                    .build();
        } else {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Документы отсутствуют!")
                    .build();
        }
    }

    private boolean isDocumentsLoaded() {
        return !CollectionUtils.isEmpty(documentRepository.findAll());
    }
}
