package com.interior.archiThink.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ServiceInvoiceItem extends InvoiceItem {

    @ManyToOne
    private ServiceType serviceType;

    public ServiceInvoiceItem() {
        setItemType(ItemType.SERVICE);
    }

    public ServiceInvoiceItem(int quantity, ServiceType serviceType) {
        this.quantity = quantity;
        this.serviceType = serviceType;
        this.price = getPrice();
        setItemType(ItemType.SERVICE);
    }

    public ItemType getItemType(){
        return ItemType.SERVICE;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public BigDecimal getPrice() {
        return serviceType.getHourlyRate().multiply(BigDecimal.valueOf(this.getQuantity()));
    }
}
