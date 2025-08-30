package com.interior.archiThink.repository;

import com.interior.archiThink.model.ProductInvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInvoiceItemRepository extends JpaRepository<ProductInvoiceItem, Long> {}
