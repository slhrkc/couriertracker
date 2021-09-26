package com.salih.migros.couriertracker.repository;

import com.salih.migros.couriertracker.entity.GeoLocationTrackRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeoLocationTrackRecordRepository extends CrudRepository<GeoLocationTrackRecord, Long> {
    public List<GeoLocationTrackRecord> findByCourierIdDateOrderedAsc(Long courierId);
}
