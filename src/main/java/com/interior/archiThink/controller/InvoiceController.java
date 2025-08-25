package com.interior.archiThink.controller;

import com.interior.archiThink.dto.InvoiceDto;
import com.interior.archiThink.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceServiceImpl invoiceTypeImpl;

    @GetMapping
    public ResponseEntity<?> getAllInvoice() {
        List<InvoiceDto> invoiceList = invoiceTypeImpl.getInvoiceList();
        if (!invoiceList.isEmpty()) {
            return ResponseEntity.ok(invoiceList);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createInvoice(@RequestBody InvoiceDto invoiceDto,
            UriComponentsBuilder uriBuilder) {
        InvoiceDto created = invoiceTypeImpl.saveInvoice(invoiceDto);
        if(created==null)
            return ResponseEntity.badRequest().build();
        var uri = uriBuilder.path("client/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created); // 201 for creation
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getInvoice(@PathVariable Long id) {
        InvoiceDto invoiceDTO = invoiceTypeImpl.getInvoice(id);
        if(Objects.nonNull(invoiceDTO)) {
            return ResponseEntity.ok(invoiceDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable Long id, @RequestBody InvoiceDto invoice) {
        InvoiceDto updated = invoiceTypeImpl.updateInvoice(invoice, id);
        if(updated == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        if (invoiceTypeImpl.deleteInvoiceById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
