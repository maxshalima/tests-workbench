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
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void rollbackMethod(WarehouseOperation operation) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public WarehouseOperationType supportedType() {
        return WarehouseOperationType.INCOMING;
    }

}
