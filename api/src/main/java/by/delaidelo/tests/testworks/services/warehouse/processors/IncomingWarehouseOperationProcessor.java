package by.delaidelo.tests.testworks.services.warehouse.processors;

import org.springframework.stereotype.Service;

import by.delaidelo.tests.testworks.dao.WarehouseOperationRepository;
import by.delaidelo.tests.testworks.domain.WarehouseOperation;
import by.delaidelo.tests.testworks.enums.WarehouseOperationType;

@Service
public class IncomingWarehouseOperationProcessor extends AbstractWarehouseOperationProcessor {

    public IncomingWarehouseOperationProcessor(WarehouseOperationRepository opres) {
        super(opres);
    }

    @Override
    protected void processMethod(WarehouseOperation operation) {
        operation.setRemainingQuantity(operation.getQuantity());
        operation.setCalculatedCosts(operation.getPrice());
    }

    @Override
    protected void rollbackMethod(WarehouseOperation operation) {
        final var outgoingOperations = opres.findBySourceId(operation.getId());
        final var referencies = opres.findByReferenceId(operation.getId());
        if(outgoingOperations.size()>0 || referencies.size()>0) {
            throw new IllegalStateException("cannot rollback used operation");
        }

        operation.setCalculatedCosts(null);
        operation.setRemainingQuantity(null);
    }

    @Override
    public WarehouseOperationType supportedType() {
        return WarehouseOperationType.INCOMING;
    }

}
