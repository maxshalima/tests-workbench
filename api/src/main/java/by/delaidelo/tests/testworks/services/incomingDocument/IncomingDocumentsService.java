package by.delaidelo.tests.testworks.services.incomingDocument;

import by.delaidelo.tests.testworks.dao.IncomingDocumentRepository;
import by.delaidelo.tests.testworks.dao.WarehouseItemTypeRepository;
import by.delaidelo.tests.testworks.domain.IncomingDocument;
import by.delaidelo.tests.testworks.domain.IncomingDocumentItem;
import by.delaidelo.tests.testworks.domain.WarehouseItemType;
import by.delaidelo.tests.testworks.dto.IncomingDocumentDto;
import by.delaidelo.tests.testworks.mappers.IncomingDocumentMapper;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IncomingDocumentsService {
    private final IncomingDocumentRepository incomingDocumentRepository;
    private final WarehouseItemTypeRepository warehouseItemTypeRepository;
    private final IncomingDocumentMapper mapper;

    public IncomingDocumentsService(IncomingDocumentRepository incomingDocumentRepository,
                                    IncomingDocumentMapper mapper,
                                    WarehouseItemTypeRepository warehouseItemTypeRepository) {
        this.incomingDocumentRepository = incomingDocumentRepository;
        this.warehouseItemTypeRepository = warehouseItemTypeRepository;
        this.mapper = mapper;
    }

    public Page<IncomingDocumentDto> findIncomingDocuments(String query, Pageable pageable) {
        return incomingDocumentRepository.findByDocumentNumberContainingIgnoreCase(query, pageable)
                .map(mapper::toDto);
    }


    public Long create(IncomingDocumentDto dto) {
        IncomingDocument document = mapper.fromDto(dto);

        for (IncomingDocumentItem item : document.getItems()) {
            item.setIncomingDocument(document);

            Long typeId = item.getWarehouseItemType().getId(); // assuming you expose this
            WarehouseItemType type = warehouseItemTypeRepository.findById(typeId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid warehouseItemTypeId: " + typeId));
            item.setWarehouseItemType(type);
        }

        incomingDocumentRepository.save(document);
        return document.getId();
        }


    public void update(@NotNull Long id, @NotNull IncomingDocumentDto dto) {
        final var incomingDocument = incomingDocumentRepository.findById(id).orElseThrow();
        mapper.update(incomingDocument, dto);
        incomingDocumentRepository.save(incomingDocument);
    }


//    public void switchState(@NotNull Long id) {
//        final var incomingDocument = incomingDocumentRepository.findById(id).orElseThrow();
//        incomingDocument.setEnabled(!incomingDocument.isEnabled());
//    }


    public void delete(@NotNull Long id) {
        final var incomingDocument = incomingDocumentRepository.findById(id).orElseThrow();
        incomingDocumentRepository.delete(incomingDocument);
    }

    public IncomingDocumentDto findById(Long id) {
        return incomingDocumentRepository.findById(id)
            .map(mapper::toDto)
            .orElseThrow();
    }
}
