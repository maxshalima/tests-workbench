package by.delaidelo.tests.testworks.dto;

import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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
