package by.delaidelo.tests.testworks.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomingDocumentDto {

    private Long id;

    @NotNull
    private LocalDate documentDate;

    @NotNull
    @Size(min = 10, max = 10)
    private String documentNumber;

    @NotNull
    @Valid
    private SelectListItemDto contractor;

    @NotNull
    @Valid
    private SelectListItemDto contract;

    @Valid
    private SelectListItemDto warehouse;

    private List<IncomingDocumentItemDto> items;

}
