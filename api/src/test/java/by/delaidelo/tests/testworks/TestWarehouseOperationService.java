//package by.delaidelo.tests.testworks;
//
//import by.delaidelo.tests.testworks.dao.WarehouseItemTypeRepository;
//import by.delaidelo.tests.testworks.dao.WarehouseOperationRepository;
//import by.delaidelo.tests.testworks.dao.WarehouseRepositry;
//import by.delaidelo.tests.testworks.domain.WarehouseOperation;
//import by.delaidelo.tests.testworks.enums.OperationManagementDocumentType;
//import by.delaidelo.tests.testworks.enums.WarehouseOperationState;
//import by.delaidelo.tests.testworks.enums.WarehouseOperationType;
//import by.delaidelo.tests.testworks.services.warehouse.WarehouseOperationService;
//import by.delaidelo.tests.testworks.utils.WarehouseOperationBuilder;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@ComponentScan
//public class TestWarehouseOperationService {
//    @Autowired
//    private WarehouseOperationService service;
//    @Autowired
//    private WarehouseOperationRepository opres;
//    @Autowired
//    private WarehouseRepositry warehouseRepositry;
//    @Autowired
//    private WarehouseItemTypeRepository itemTypeRepository;
//
//     public TestWarehouseOperationService(WarehouseOperationService service, WarehouseOperationRepository opres,
//             WarehouseRepositry warehouseRepositry, WarehouseItemTypeRepository itemTypeRepository) {
//         this.service = service;
//         this.opres = opres;
//         this.warehouseRepositry = warehouseRepositry;
//         this.itemTypeRepository = itemTypeRepository;
//     }
//
//    private WarehouseOperation generateIncomingOperation() {
//        final var warehouse = warehouseRepositry.findById(1L).orElseThrow();
//        final var builder = WarehouseOperationBuilder.builder();
//        final var operation = builder
//                .type(WarehouseOperationType.INCOMING)
//                .operationDate(LocalDate.now())
//                .ownerDocumentType(OperationManagementDocumentType.WAREHOUSE_INCOMING_DOCUMENT)
//                .ownerDocumentId(1L)
//                .ownerDocumentInfo("Some doc info")
//                .warehouse(warehouse)
//                .itemType(itemTypeRepository.findById(1L).orElseThrow())
//                .quantity(BigDecimal.valueOf(100))
//                .price(BigDecimal.valueOf(100))
//                .totalSum(BigDecimal.valueOf(100).multiply(BigDecimal.valueOf(100L)))
//                .build();
//        return operation;
//    }
//
//    @Test
//    @Transactional
//    void testCreateIncoming() {
//        final var operation = generateIncomingOperation();
//        final var operationId = service.create(operation);
//        assertNotNull(operationId);
//        final var createdOperationOpt = opres.findById(operationId);
//        assertTrue(createdOperationOpt.isPresent());
//        final var newOp = createdOperationOpt.get();
//        assertNull(newOp.getSource());
//        assertNull(newOp.getReference());
//        assertNull(newOp.getRemainingQuantity());
//        assertNull(newOp.getCalculatedCosts());
//        assertEquals(BigDecimal.valueOf(100), newOp.getPrice());
//        assertEquals(BigDecimal.valueOf(100), newOp.getQuantity());
//    }
//
//    @Test
//    @Transactional
//    void createAndProcess() {
//        final var operation = generateIncomingOperation();
//        final var operationId = service.create(operation);
//        assertNotNull(operationId);
//        service.process(operationId);
//        final var processedOperation = opres.findById(operationId).orElseThrow();
//        assertNotEquals(WarehouseOperationState.PREPARED, processedOperation.getState());
//        assertEquals(processedOperation.getQuantity(), processedOperation.getRemainingQuantity());
//        assertEquals(processedOperation.getCalculatedCosts(), processedOperation.getPrice());
//    }
//
//}
