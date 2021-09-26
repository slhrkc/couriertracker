package com.salih.migros.couriertracker.entity;

import com.salih.migros.couriertracker.model.IGeoLocation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.Date;

@Entity
@NamedQuery(name = "GeoLocationTrackRecord.findByCourierIdDateOrderedAsc", query = "from GeoLocationTrackRecord where courierId = :courierId order by transactionDate asc")
public class GeoLocationTrackRecord implements IGeoLocation {
    @Id
    @GeneratedValue
    private Long id;
    private Long courierId;
    private Date transactionDate;
    private double lat;
    private double lon;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
