package by.delaidelo.tests.testworks.services.incomingDocument;

import by.delaidelo.tests.testworks.dao.IncomingDocumentRepository;
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
    private final IncomingDocumentMapper mapper;

    public IncomingDocumentsService(IncomingDocumentRepository incomingDocumentRepository, IncomingDocumentMapper mapper) {
        this.incomingDocumentRepository = incomingDocumentRepository;
        this.mapper = mapper;
    }

    public Page<IncomingDocumentDto> findIncomingDocuments(String query, Pageable pageable) {
        return incomingDocumentRepository.findByTitleContainingIgnoreCase(query, pageable)
                .map(mapper::toDto);
    }


    public Long create(IncomingDocumentDto dto) {
        final var w = mapper.fromDto(dto);
        incomingDocumentRepository.save(w);
        return w.getId();
    }


    public void update(@NotNull Long id, @NotNull IncomingDocumentDto dto) {
        final var incomingDocument = incomingDocumentRepository.findById(id).orElseThrow();
        mapper.update(incomingDocument, dto);
        incomingDocumentRepository.save(incomingDocument);
    }


    public void switchState(@NotNull Long id) {
        final var incomingDocument = incomingDocumentRepository.findById(id).orElseThrow();
        incomingDocument.setEnabled(!incomingDocument.isEnabled());
    }


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
