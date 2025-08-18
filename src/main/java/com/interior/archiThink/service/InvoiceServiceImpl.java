package com.interior.archiThink.service;

import com.interior.archiThink.dto.InvoiceDto;
import com.interior.archiThink.mapper.InvoiceMapper;
import com.interior.archiThink.model.*;
import com.interior.archiThink.repository.ClientRepository;
import com.interior.archiThink.repository.InvoiceRepository;
import com.interior.archiThink.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private InvoiceMapper invoiceMapper;

    @Override
    public InvoiceDto saveInvoice(InvoiceDto invoiceDTO) {
        Invoice invoice = invoiceMapper.toEntity(invoiceDTO);
        Client client = clientRepository.findById(invoiceDTO.getClient().getId()).orElse(null);
        invoice.setClient(client);
        Project project = projectRepository.findById(invoiceDTO.getProject().getId()).orElse(null);
        invoice.setProject(project);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDto(savedInvoice);
    }

    @Override
    public InvoiceDto getInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElse(null);
        if(Objects.nonNull(invoice))
            return invoiceMapper.toDto(invoice);
        return null;
    }

    @Override
    public List<InvoiceDto> getInvoiceList() {
        /*List<Invoice> all = invoiceRepository.findAll();
        List<InvoiceDto> result = all.stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
        return result;*/
        /*return invoiceRepository.findAll()
                .stream()
                .map(invoiceMapper::toDto)
                .collect(Collectors.toList());*/
        List<Invoice> all = invoiceRepository.findAll();
        return invoiceMapper.toDtoList(all);
    }

    @Override
    @Transactional
    public InvoiceDto updateInvoice(InvoiceDto invoiceDTO, Long id) {
        Invoice existingInvoice = invoiceRepository.findById(id).
            orElseThrow(() -> new EntityNotFoundException("Invoice not found"));

        if(Objects.nonNull(existingInvoice)) {
            existingInvoice.setName(invoiceDTO.getName());
            Client client = clientRepository.findById(invoiceDTO.getClient().getId()).orElse(null);
            existingInvoice.setClient(client);
            Project project = projectRepository.findById(invoiceDTO.getProject().getId()).orElse(null);
            existingInvoice.setProject(project);
            existingInvoice.setTotalPrice(invoiceDTO.getTotalPrice());
            existingInvoice.setPaymentDate(invoiceDTO.getPaymentDate());
            existingInvoice.setStatus(invoiceDTO.getStatus());
            // Replace items
            if (Objects.nonNull(invoiceDTO.getItems()) && !invoiceDTO.getItems().isEmpty()){
                existingInvoice.getItems().addAll(invoiceMapper.toItemEntityList(invoiceDTO.getItems()));
            }
            Invoice updatedInvoice = invoiceRepository.save(existingInvoice);
            return invoiceMapper.toDto(updatedInvoice);
        } else {
            return null;
        }

    }

    @Override
    public Boolean deleteInvoiceById(Long invoiceId) {
        if (!invoiceRepository.existsById(invoiceId)) {
            throw new RuntimeException("Invoice not found with id: " + invoiceId);
        }
        invoiceRepository.deleteById(invoiceId);
        return Boolean.TRUE;
    }
}
