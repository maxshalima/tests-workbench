package by.delaidelo.tests.testworks.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomingDocumentItemDto {

    //TODO pass id on fetch
    @JsonIgnore
    private Long id;

    private Long warehouseItemTypeId;
    private String warehouseItemTypeTitle;
    private Integer quantity;
    private BigDecimal price;
}
