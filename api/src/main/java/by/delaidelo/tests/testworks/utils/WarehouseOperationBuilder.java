package by.delaidelo.tests.testworks.utils;

import java.math.BigDecimal;
import java.util.Objects;

import by.delaidelo.tests.testworks.domain.Warehouse;
import by.delaidelo.tests.testworks.domain.WarehouseItemType;
import by.delaidelo.tests.testworks.domain.WarehouseOperation;
import by.delaidelo.tests.testworks.enums.OperationManagementDocumentType;
import by.delaidelo.tests.testworks.enums.WarehouseOperationType;
import jakarta.validation.ValidationException;

public class WarehouseOperationBuilder {

    private WarehouseOperation op = new WarehouseOperation();

    public WarehouseOperationBuilder type(WarehouseOperationType type) {
        op.setType(type);
        return this;
    }

    public WarehouseOperationBuilder warehouse(Warehouse warehouse) {
        op.setWarehouse(warehouse);
        return this;
    }

    public WarehouseOperationBuilder itemType(WarehouseItemType itemType) {
        op.setItemType(itemType);
        return this;
    }

    public WarehouseOperationBuilder ownerDocumentType(OperationManagementDocumentType docType) {
        op.setOwnerDocType(docType);
        return this;
    }

    public WarehouseOperationBuilder ownerDocumentId(Long documentId) {
        op.setOwnerDocId(documentId);
        return this;
    }

    public WarehouseOperationBuilder source(WarehouseOperation source) {
        op.setSource(source);
        return this;
    }

    public WarehouseOperationBuilder reference(WarehouseOperation ref) {
        this.op.setReference(ref);
        return this;
    }

    public WarehouseOperationBuilder quantity(BigDecimal quantity) {
        op.setQuantity(quantity);
        return this;
    }

    public WarehouseOperationBuilder price(BigDecimal price) {
        op.setPrice(price);
        return this;
    }

    public WarehouseOperationBuilder totalSum(BigDecimal totalSum) {
        op.setTotalSum(totalSum);
        return this;
    }

    public WarehouseOperationBuilder ownerDocumentInfo(String docInfo) {
        op.setOwnerDocInfo(docInfo);
        return this;
    }
    public WarehouseOperation build() {
        validate(op);
        return op;
    }

    private void validate(WarehouseOperation op) {
        if (op.getType() == null)
            throw new ValidationException("type is required");
        if (Objects.isNull(op.getWarehouse()))
            throw new ValidationException("warehouse is required");
        if (Objects.isNull(op.getItemType()))
            throw new ValidationException();
        if (Objects.isNull(op.getQuantity()) || op.getQuantity().compareTo(BigDecimal.ZERO) == 0)
            throw new ValidationException();
        if (Objects.isNull(op.getSource()) && op.getType() == WarehouseOperationType.OUTGOING)
            throw new ValidationException();
        if (Objects.isNull(op.getReference()) && (op.getType() == WarehouseOperationType.COSTS_ALLOCATION
                || op.getType() == WarehouseOperationType.COSTS_WRITE_OFF))
            throw new ValidationException();
    }

}
