package com.salih.migros.couriertracker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.Date;

@Entity
@NamedQuery(name = "CourierStoreEntrance.findByCourierAndStoreNameAndTimeRange", query = "from CourierStoreEntrance where storeName = :storeName and courierId = :courierId and transactionDate >= :transactionDate order by transactionDate desc")
public class CourierStoreEntrance {
    @Id
    @GeneratedValue
    private Long id;
    private Long courierId;
    private String storeName;
    private Date transactionDate;

    public CourierStoreEntrance() {
        super();
    }

    public CourierStoreEntrance(Long courierId, String storeName, Date transactionDate) {
        super();
        this.courierId = courierId;
        this.storeName = storeName;
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
