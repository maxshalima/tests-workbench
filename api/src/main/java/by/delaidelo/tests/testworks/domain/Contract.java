package by.delaidelo.tests.testworks.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contracts")
@Getter
@Setter
public class Contract extends AbstractEntity {
    @ManyToOne
    @JoinColumn
    private Contractor contractor;
    @Column
    private LocalDate contractDate;
    @Column
    private String contractNumber;
    @Column
    private String description;
}
