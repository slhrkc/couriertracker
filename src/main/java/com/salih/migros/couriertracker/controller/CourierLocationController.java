package com.salih.migros.couriertracker.controller;

import com.salih.migros.couriertracker.model.SaveGeoLocationRequest;
import com.salih.migros.couriertracker.service.CourierLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courierLocation")
public class CourierLocationController {
    @Autowired
    CourierLocationService courierLocationService;

    @PostMapping("/")
    public String save(@RequestBody SaveGeoLocationRequest request) throws Exception {
        courierLocationService.saveCourierLocation(request.getCourier(),request.getLat(),request.getLon(),request.getTransactionTime());
        return null;
    }

    @GetMapping("/distanceTravelled")
    public String getdistanceTravelled(@RequestParam Long courierId){
        return courierLocationService.getTotalTravelDistance(courierId).toString();
    }

}