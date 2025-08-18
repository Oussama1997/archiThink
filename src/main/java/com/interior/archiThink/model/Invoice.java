package com.interior.archiThink.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY) // Many invoices can belong to one client
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime creationDate;

    @LastModifiedDate
    private LocalDateTime modificationDate;

    private LocalDateTime paymentDate;
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItem> items;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public Invoice() {
    }

    public Invoice(Client client, BigDecimal totalPrice, InvoiceStatus status) {
        this.client = client;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
        if (status == InvoiceStatus.PAYEE) {
            this.paymentDate = LocalDateTime.now();
        }
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }

    public BigDecimal getPrice() {
        return items.stream()
                .map(InvoiceItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}