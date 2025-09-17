package edu.co.icesi.examjpatemplate.repository;

import edu.co.icesi.examjpatemplate.entity.GeoPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GeoPointRepository extends JpaRepository<GeoPoint, Integer> {
    List<GeoPoint> findByBusLicensePlateIgnoreCaseOrderByTimestampAsc(String licensePlate);

    Optional<GeoPoint> findFirstByBusLicensePlateIgnoreCaseOrderByTimestampDesc(String licensePlate);
}
