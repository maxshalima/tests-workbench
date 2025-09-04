package by.delaidelo.tests.testworks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDto {
    private Long id;
    private String title;
    private String address;
    private boolean enabled = true;
}
