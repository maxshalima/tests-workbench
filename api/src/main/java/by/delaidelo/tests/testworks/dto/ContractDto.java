package by.delaidelo.tests.testworks.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ContractDto {
    private Long id;
    private SelectListItemDto contractor;
    private LocalDate contractDate;
    private String contractNumber;
    private String description;
}
