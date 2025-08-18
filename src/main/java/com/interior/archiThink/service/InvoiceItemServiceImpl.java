package com.interior.archiThink.service;

import com.interior.archiThink.model.InvoiceItem;
import com.interior.archiThink.repository.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceItemServiceImpl implements InvoiceItemService {

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Override
    public InvoiceItem save(InvoiceItem item) {
        return invoiceItemRepository.save(item);
    }

    @Override
    public List<InvoiceItem> findAll() {
        return invoiceItemRepository.findAll();
    }

    @Override
    public InvoiceItem getItem(Long id) {
        return invoiceItemRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean deleteById(Long invoiceId) {
        if (!invoiceItemRepository.existsById(invoiceId)) {
            throw new RuntimeException("Invoice not found with id: " + invoiceId);
        }
        invoiceItemRepository.deleteById(invoiceId);
        return Boolean.TRUE;
    }

    @Override
    public InvoiceItem getItemsByInvoice(Long id) {
        return null;
    }
}
