package by.delaidelo.tests.testworks.domain;

import java.math.BigDecimal;

import by.delaidelo.tests.testworks.enums.OperationManagementDocumentType;
import by.delaidelo.tests.testworks.enums.WarehouseOperationState;
import by.delaidelo.tests.testworks.enums.WarehouseOperationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "warehouse_operations")
@Getter
@Setter
public class WarehouseOperation extends AbstractEntity {

    /**
     * Склад, по которому совершается операция
     */
    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false, updatable = false)
    private Warehouse warehouse;

    /**
     * Вид ТМЦ с которым совершается операция
     */
    @ManyToOne
    @JoinColumn(name = "item_type_id", nullable = false, updatable = false)
    private WarehouseItemType itemType;

    /**
     * Вид операции
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type", nullable = false, updatable = false)
    private WarehouseOperationType type;

    /**
     * Статус операции, при создании всегда должно быть PREPARED
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "operation_state", nullable = false)
    private WarehouseOperationState state = WarehouseOperationState.PREPARED;

    /**
     * Тип докумета создавшего операцию
     */
    @Column(name = "owner_doc_type", nullable = false, updatable = false)
    private OperationManagementDocumentType ownerDocType;
    /**
     * ИД документа создавшего операцию
     */
    @Column(name = "owner_doc_id", nullable = false, updatable = false)
    private long ownerDocId;

    /**
     * Кол-во едениц указанного ТМЦ
     */
    @Column(nullable = false, updatable = false)
    private BigDecimal quantity = BigDecimal.ZERO;

    /**
     * Стоимость единицы указанного ТМЦ по документу (FIXME: возможно тут лучше указывать с/с)
     * Для распределения затрат, списания стоимости - сумма изменения единицы с/с товара
     */
    @Column(nullable = false, updatable = false)
    private BigDecimal price = BigDecimal.ZERO;

    /**
     * Общая стомость по операции
     */
    @Column(nullable = false, updatable = false)
    private BigDecimal totalSum = BigDecimal.ZERO;

    /**
     * Для исходящих операций в этом поле указывается источник
     */
    @ManyToOne
    @JoinColumn(name = "source_op_id", updatable = false)
    private WarehouseOperation source;

    /**
     * Для операций распределения и списания стоимости в этом поле указывается над какой операцией производится действие
     */
    @ManyToOne
    @JoinColumn(name = "reference_op_id", updatable = false)
    private WarehouseOperation reference;

    private BigDecimal remaingQuantity;

    /**
     * Себестоимость единицы ТМЦ, при создании входящей операции по умолчанию должно ровняться цене
     */
    private BigDecimal costs;
}
