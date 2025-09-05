package by.delaidelo.tests.testworks.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "contractors")
public class Contractor extends AbstractEntity {
    @Column
    private String taxNumber;
    @Column
    private String title;
    @Column
    private String shortTitle;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private String email;
    @Column
    private String contractPerson;
}
