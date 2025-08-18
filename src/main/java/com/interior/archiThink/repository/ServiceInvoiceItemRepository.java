package com.interior.archiThink.repository;

import com.interior.archiThink.model.ServiceInvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceInvoiceItemRepository extends JpaRepository<ServiceInvoiceItem, Long> {
}
