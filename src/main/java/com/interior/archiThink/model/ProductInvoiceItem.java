package com.interior.archiThink.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ProductInvoiceItem extends InvoiceItem {

    private String product;
    private BigDecimal unitPrice;

    public ProductInvoiceItem() {
        setItemType(ItemType.PRODUIT);
    }

    public ProductInvoiceItem(int quantity, BigDecimal unitPrice, String product) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.product = product;
        this.price = getPrice();
        setItemType(ItemType.PRODUIT);
    }

    public ItemType getItemType(){
        return ItemType.PRODUIT;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getPrice() {
        return this.unitPrice.multiply(BigDecimal.valueOf(this.getQuantity()));
    }
}
