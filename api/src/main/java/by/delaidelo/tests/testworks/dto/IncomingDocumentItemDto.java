package by.delaidelo.tests.testworks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomingDocumentItemDto {
    private Long id;
    private Long warehouseItemTypeId;
    private Integer quantity;
    private BigDecimal price;
}
