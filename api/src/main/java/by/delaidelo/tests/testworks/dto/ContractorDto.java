package by.delaidelo.tests.testworks.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContractorDto {
    private Long id;

    @NotNull
    @Size(min = 9, max = 9)
    private String taxNumber;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    @Size(max = 255)
    private String shortTitle;

    private String address;

    private String phone;
    
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String contactPerson;
}
