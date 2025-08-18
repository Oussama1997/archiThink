package com.interior.archiThink.repository;

import com.interior.archiThink.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {}

