package com.interior.archiThink.service;

import com.interior.archiThink.dto.InvoiceDto;

import java.util.List;

public interface InvoiceService {
    InvoiceDto saveInvoice(InvoiceDto invoice);
    InvoiceDto getInvoice(Long invoiceId);
    List<InvoiceDto> getInvoiceList();
    InvoiceDto updateInvoice(InvoiceDto invoice, Long invoiceId);
    Boolean deleteInvoiceById(Long invoiceId);
}
