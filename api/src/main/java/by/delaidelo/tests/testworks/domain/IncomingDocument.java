package by.delaidelo.tests.testworks.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "incoming_documents")
public class IncomingDocument extends AbstractEntity {

    @Column
    private LocalDate documentDate;

    @Column
    private String documentNumber;

    @ManyToOne
    @JoinColumn
    private Contractor contractor;

    @ManyToOne
    @JoinColumn
    private Contract contract;

    @ManyToOne
    @JoinColumn
    private Warehouse warehouse;

    @OneToMany(mappedBy = "incomingDocument", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IncomingDocumentItem> items;
}
