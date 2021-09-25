package com.salih.migros.couriertracker.entity;

import com.salih.migros.couriertracker.model.GeoLocation;
import com.salih.migros.couriertracker.model.IGeoLocation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class GeoLocationTrackRecord extends GeoLocation implements IGeoLocation {
    @Id
    @GeneratedValue
    private Long id;
    private Long courierId;
    private Date transactionDate;

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
