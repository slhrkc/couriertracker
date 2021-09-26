package com.salih.migros.couriertracker.repository;

import com.salih.migros.couriertracker.entity.CourierStoreEntrance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CourierStoreEntraceRepository extends CrudRepository<CourierStoreEntrance, Integer> {
    List<CourierStoreEntrance> findByCourierAndStoreNameAndTimeRange(@Param("courierId")Long courierId, @Param("storeName") String storeName,@Param("transactionDate") Date transactionDate);
    List<CourierStoreEntrance> findByCourierIdOrderByTransactionDateDesc(@Param("courierId")Long courierId);

}
