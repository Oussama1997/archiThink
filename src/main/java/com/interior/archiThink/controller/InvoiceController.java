package com.interior.archiThink.controller;

import com.interior.archiThink.dto.InvoiceDto;
import com.interior.archiThink.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> createInvoice(@RequestBody InvoiceDto invoice) {
        try {
            InvoiceDto created = invoiceTypeImpl.saveInvoice(invoice);
            return new ResponseEntity<>(created, HttpStatus.CREATED); // 201 for creation
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the invoice", HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
        try{
            InvoiceDto updated = invoiceTypeImpl.updateInvoice(invoice, id);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the invoice", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteInvoice(@PathVariable Long id) {
        if (invoiceTypeImpl.deleteInvoiceById(id)) {
            return ResponseEntity.ok("Invoice deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
