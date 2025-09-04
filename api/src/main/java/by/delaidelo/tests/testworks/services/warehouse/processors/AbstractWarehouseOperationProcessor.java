package by.delaidelo.tests.testworks.services.warehouse.processors;

import org.springframework.transaction.annotation.Transactional;

import by.delaidelo.tests.testworks.dao.WarehouseOperationRepository;
import by.delaidelo.tests.testworks.domain.WarehouseOperation;
import by.delaidelo.tests.testworks.enums.WarehouseOperationState;
import by.delaidelo.tests.testworks.enums.WarehouseOperationType;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractWarehouseOperationProcessor {

    protected final WarehouseOperationRepository opres;

    public abstract WarehouseOperationType supportedType();

    protected abstract void processMethod(WarehouseOperation operation);

    protected abstract void rollbackMethod(WarehouseOperation operation);

    @Transactional
    public void process(@NotNull WarehouseOperation operation) {
        if (operation.getType() != supportedType()) {
            throw new IllegalStateException("Wrong operation type");
        }

        // Skip operations which are already processed
        if (operation.getState() != WarehouseOperationState.PREPARED)
            return;

        processMethod(operation);
        operation.setState(WarehouseOperationState.PROCESSED);
        opres.save(operation);
    }

    @Transactional
    public void rollback(@NotNull WarehouseOperation operation) {
        if (operation.getType() != supportedType()) {
            throw new IllegalStateException("Wrong operation type");
        }

        // Skip operations which are already in prepared state
        if (operation.getState() != WarehouseOperationState.PROCESSED)
            return;

        rollbackMethod(operation);
        operation.setState(WarehouseOperationState.PREPARED);
        opres.save(operation);
    }
}
