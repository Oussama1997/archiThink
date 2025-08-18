package com.interior.archiThink.service;

import com.interior.archiThink.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemService {
    InvoiceItem save(InvoiceItem item);
    List<InvoiceItem> findAll();
    InvoiceItem getItem(Long id);
    Boolean deleteById(Long id);
    InvoiceItem getItemsByInvoice(Long id);
}
