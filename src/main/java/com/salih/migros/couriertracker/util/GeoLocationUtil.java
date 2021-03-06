package com.salih.migros.couriertracker.util;

import com.salih.migros.couriertracker.model.IGeoLocation;

import java.util.List;

public class GeoLocationUtil {
    private static GeoLocationUtil instance = new GeoLocationUtil();

    private GeoLocationUtil() {
    }

    public static GeoLocationUtil getInstance(){
        return instance;
    }

    public  double distance(double lat1, double lon1, double lat2,double lon2) {

        final int EARTH_RADIUS_IN_METERS = 6371 * 1000;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS_IN_METERS * c;

        return distance;
    }

    public  double totalDistance(List<? extends IGeoLocation> geoLocationList){
        double totalDistance = 0.0;
        IGeoLocation currentLocation = null;

        for (IGeoLocation geoLocation:geoLocationList){
            if (currentLocation == null){
                currentLocation = geoLocation;
                continue;
            }
            totalDistance += distance(currentLocation.getLat(),currentLocation.getLon(),geoLocation.getLat(),geoLocation.getLon());
            currentLocation = geoLocation;
        }
        return totalDistance;
    }

    public  boolean isLocationValid(double lat, double lon){
        if ((lon > -180 && lon < 180) && (lat > -90 && lat < 90)) {
            return true;
        }
        return false;
    }
}
