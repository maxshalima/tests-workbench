package by.delaidelo.tests.testworks.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContractDto {
    private Long id;
    @NotNull
    @Valid
    private SelectListItemDto contractor;
    private LocalDate contractDate;
    private String contractNumber;
    private String description;
}
