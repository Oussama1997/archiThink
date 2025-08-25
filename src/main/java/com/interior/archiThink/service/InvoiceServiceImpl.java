package com.interior.archiThink.service;

import com.interior.archiThink.dto.InvoiceDto;
import com.interior.archiThink.mapper.InvoiceMapper;
import com.interior.archiThink.model.*;
import com.interior.archiThink.repository.ClientRepository;
import com.interior.archiThink.repository.InvoiceRepository;
import com.interior.archiThink.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    InvoiceMapper invoiceMapper;

    @Override
    public InvoiceDto saveInvoice(InvoiceDto invoiceDTO) {
        Invoice savedInvoice = invoiceMapper.toEntity(invoiceDTO);
        Client client = clientRepository.findById(invoiceDTO.getClient().getId()).orElse(null);
        savedInvoice.setClient(client);
        Project project = projectRepository.findById(invoiceDTO.getProject().getId()).orElse(null);
        savedInvoice.setProject(project);
        invoiceRepository.save(savedInvoice);
        invoiceDTO.setId(savedInvoice.getId());
        return invoiceDTO;
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
        Invoice existingInvoice = invoiceRepository.findById(id).orElse(null);
        if(existingInvoice == null)
            return null;
        Client client = clientRepository.findById(invoiceDTO.getClient().getId()).orElse(null);
        if(client == null)
            return null;
        existingInvoice.setClient(client);
        Project project = projectRepository.findById(invoiceDTO.getProject().getId()).orElse(null);
        if(project == null)
            return null;
        existingInvoice.setProject(project);
        existingInvoice.setName(invoiceDTO.getName());
        existingInvoice.setTotalPrice(invoiceDTO.getTotalPrice());
        existingInvoice.setPaymentDate(invoiceDTO.getPaymentDate());
        existingInvoice.setStatus(invoiceDTO.getStatus());
        // Replace items
        if (Objects.nonNull(invoiceDTO.getItems()) && !invoiceDTO.getItems().isEmpty()){
            existingInvoice.getItems().addAll(invoiceMapper.toItemEntityList(invoiceDTO.getItems()));
        }
        Invoice updatedInvoice = invoiceRepository.save(existingInvoice);
        return invoiceMapper.toDto(updatedInvoice);
    }

    @Override
    public Boolean deleteInvoiceById(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElse(null);
        if (invoice == null)
            return Boolean.FALSE;
        invoiceRepository.delete(invoice);
        return Boolean.TRUE;
    }
}
