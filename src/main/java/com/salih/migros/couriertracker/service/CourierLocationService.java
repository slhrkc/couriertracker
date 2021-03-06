package com.salih.migros.couriertracker.service;

import com.salih.migros.couriertracker.entity.CourierStoreEntrance;
import com.salih.migros.couriertracker.model.Courier;

import java.util.Date;
import java.util.List;

public interface CourierLocationService {
    public void saveCourierLocation(Courier courier, double lat, double lon, Date transactionDate) throws Exception;
    public Double getTotalTravelDistance(Long courierId);
    public List<CourierStoreEntrance> getStoreEntrances(Long courierId);
}
