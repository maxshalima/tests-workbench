package by.delaidelo.tests.testworks.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "incoming_document_items")
public class IncomingDocumentItem extends AbstractEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "incoming_document_id")
    private IncomingDocument incomingDocument;

    @ManyToOne(optional = false)
    @JoinColumn(name = "warehouse_item_type_id")
    private WarehouseItemType warehouseItemType;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
}
