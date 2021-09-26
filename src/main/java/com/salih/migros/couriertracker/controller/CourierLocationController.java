package com.salih.migros.couriertracker.controller;

import com.salih.migros.couriertracker.entity.CourierStoreEntrance;
import com.salih.migros.couriertracker.model.GetDistanceTravelledResponse;
import com.salih.migros.couriertracker.model.SaveGeoLocationRequest;
import com.salih.migros.couriertracker.service.CourierLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courierLocation")
public class CourierLocationController {
    @Autowired
    CourierLocationService courierLocationService;

    @PostMapping("/")
    public void save(@RequestBody SaveGeoLocationRequest request) throws Exception {
        courierLocationService.saveCourierLocation(request.getCourier(),request.getLat(),request.getLon(),request.getTransactionTime());
    }

    @GetMapping("/travelledDistance")
    public GetDistanceTravelledResponse getdistanceTravelled(@RequestParam Long courierId){
        GetDistanceTravelledResponse response = new GetDistanceTravelledResponse();
        response.setTotalDistance(courierLocationService.getTotalTravelDistance(courierId));
        return response;
    }

    @GetMapping("/storeEntrances")
    public List<CourierStoreEntrance> getStoreEntrances(@RequestParam Long courierId){
        return courierLocationService.getStoreEntrances(courierId);
    }

}