package by.delaidelo.tests.testworks.services;

import java.util.List;

import org.springframework.stereotype.Service;

import by.delaidelo.tests.testworks.dao.WarehouseOperationRepository;
import by.delaidelo.tests.testworks.domain.WarehouseOperation;
import by.delaidelo.tests.testworks.enums.WarehouseOperationState;
import by.delaidelo.tests.testworks.services.warehouse.processors.AbstractWarehouseOperationProcessor;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WarehouseOperationService {

    private final WarehouseOperationRepository opres;
    private final List<AbstractWarehouseOperationProcessor> processors;

    @Transactional
    public Long create(@NotNull WarehouseOperation operation) {
        operation.setState(WarehouseOperationState.PREPARED);
        operation.setCalculatedCosts(null);
        operation.setRemainingQuantity(null);
        switch (operation.getType()) {
            case INCOMING -> {
                operation.setSource(null);
                operation.setReference(null);
            }
            case OUTGOING -> {
                operation.setReference(null);
            }
            case COSTS_ALLOCATION, COSTS_WRITE_OFF -> {
                operation.setSource(null);
            }
            default -> {
                // Do nothing
            }
        }
        opres.save(operation);
        return operation.getId();
    }

    /**
     * Обработка и применение действия указанного в операции, заполнение необходимых
     * сервисных полей
     * 
     * @param operationId
     */
    @Transactional
    public void process(@NotNull Long operationId) {
        final var operation = opres.findById(operationId).orElseThrow();
        final var processor = processors
                .stream()
                .filter(p -> p.supportedType() == operation.getType())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unsupported operation type!"));
        processor.process(operation);
    }

    /**
     * Отмена действия указанного в операции
     * 
     * @param operationId
     */
    @Transactional
    public void rollback(@NotNull Long operationId) {
        final var operation = opres.findById(operationId).orElseThrow();
        final var processor = processors
                .stream()
                .filter(p -> p.supportedType() == operation.getType())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unsupported operation type!"));
        processor.rollback(operation);
    }

}
