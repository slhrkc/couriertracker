package com.salih.migros.couriertracker.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salih.migros.couriertracker.exception.BadRequestException;
import com.salih.migros.couriertracker.repository.CourierStoreEntraceRepository;
import com.salih.migros.couriertracker.repository.GeoLocationTrackRecordRepository;
import com.salih.migros.couriertracker.entity.CourierStoreEntrance;
import com.salih.migros.couriertracker.entity.GeoLocationTrackRecord;
import com.salih.migros.couriertracker.model.Courier;
import com.salih.migros.couriertracker.model.Store;
import com.salih.migros.couriertracker.util.GeoLocationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class  CourierLocationServiceImpl implements CourierLocationService{
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Value("classpath:stores.json")
    private Resource resourceFile;

    @Autowired
    private CourierStoreEntraceRepository courierStoreEntraceRepository;

    @Autowired
    private GeoLocationTrackRecordRepository geoLocationTrackRecordRepository;

    private List<Store> stores = new ArrayList<>();

    private final Integer DISTANCE_TO_STORE_TO_LOG = 100;
    private final Integer ENTRANCE_INVALID_TIME_RANGE = 1;

    @PostConstruct
    public void init() throws IOException {
        try {
            stores = objectMapper.readValue(resourceFile.getInputStream(), new TypeReference<List<Store>>() {});
        }catch (IOException exception){
            throw new IOException("Error while loading store information",exception);
        }
    }

    @Override
    public void saveCourierLocation(Courier courier, double lat, double lon,Date transactionDate) throws Exception {

        if (courier == null){
            throw new BadRequestException("Courier cannot be null!");
        }
        if (courier.getCourierId() == null){
            throw new BadRequestException("Courier ID cannot be null!");
        }
        if (!GeoLocationUtil.isLocationValid(lat,lon)){
            throw new BadRequestException("Invalid geolocation!");
        }
        if (transactionDate == null){
            throw new BadRequestException("Transaction Date cannot be null!");
        }

        GeoLocationTrackRecord record = new GeoLocationTrackRecord(courier.getCourierId(),transactionDate,lat,lon);
        geoLocationTrackRecordRepository.save(record);

        logCarrierStoreEntrance(courier.getCourierId(),lat,lon,transactionDate);
    }

    @Override
    public Double getTotalTravelDistance(Long courierId) {
        List<GeoLocationTrackRecord> courierRecords = geoLocationTrackRecordRepository.findByCourierIdDateOrderedAsc(courierId);
        return GeoLocationUtil.totalDistance(courierRecords);
    }

    @Override
    public List<CourierStoreEntrance> getStoreEntrances(Long courierId) {
        return courierStoreEntraceRepository.findByCourierIdOrderByTransactionDateDesc(courierId);
    }

    private void logCarrierStoreEntrance(Long courierId, double lat, double lon,Date transactionDate){
        Calendar oneMinRange = Calendar.getInstance();
        oneMinRange.setTime(transactionDate);
        oneMinRange.add(Calendar.MINUTE,-1 * ENTRANCE_INVALID_TIME_RANGE);

        for (Store store:stores) {
            if (GeoLocationUtil.distance(lat,lon,store.getLat(),store.getLng()) <= DISTANCE_TO_STORE_TO_LOG
                    && !hasCourierEntrance(courierId,store,oneMinRange.getTime())) {
                CourierStoreEntrance entrance = new CourierStoreEntrance(courierId,store.getName(),transactionDate);
                courierStoreEntraceRepository.save(entrance);
            }
        }
    }

    private boolean hasCourierEntrance(Long courierId, Store store, Date timeRange){
        List<CourierStoreEntrance> entrances = courierStoreEntraceRepository.findByCourierAndStoreNameAndTimeRange(courierId,store.getName(),timeRange);
        if (CollectionUtils.isEmpty(entrances)){
            return false;
        }
        return true;
    }
}
