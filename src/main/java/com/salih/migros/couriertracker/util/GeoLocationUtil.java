package com.salih.migros.couriertracker.util;

import com.salih.migros.couriertracker.model.GeoLocation;
import com.salih.migros.couriertracker.model.IGeoLocation;

import java.util.List;

public class GeoLocationUtil {
    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public static double distance(double lat1, double lon1, double lat2,double lon2) {

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

    public static double totalDistance(List<? extends IGeoLocation> geoLocationList){
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

    /**
     * The latitude must be a number between -90 and 90 and the longitude between -180 and 180.
     * @param lat
     * @param lon
     * @return
     */
    public static boolean isLocationValid(double lat, double lon){
        if ((lon > -180 && lon < 180) && (lat > -90 && lat < 90)) {
            return true;
        }
        return false;
    }
}
