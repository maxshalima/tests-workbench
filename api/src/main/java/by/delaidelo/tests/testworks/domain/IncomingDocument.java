package by.delaidelo.tests.testworks.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "incoming_documents")
public class IncomingDocument extends AbstractEntity {
    @Column
    private String title;

    @Column
    private String address;

    @Column(name = "is_enabled", nullable = false)
    private boolean enabled;
}
