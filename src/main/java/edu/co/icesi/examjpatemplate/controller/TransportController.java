package edu.co.icesi.examjpatemplate.controller;

import edu.co.icesi.examjpatemplate.entity.Bus;
import edu.co.icesi.examjpatemplate.entity.GeoPoint;
import edu.co.icesi.examjpatemplate.repository.BusRepository;
import edu.co.icesi.examjpatemplate.repository.GeoPointRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransportController {

    private final BusRepository busRepository;
    private final GeoPointRepository geoPointRepository;

    public TransportController(BusRepository busRepository, GeoPointRepository geoPointRepository) {
        this.busRepository = busRepository;
        this.geoPointRepository = geoPointRepository;
    }

    @GetMapping("/routes/{routeName}/buses")
    public ResponseEntity<List<Bus>> getBusesByRouteName(@PathVariable String routeName) {
        return ResponseEntity.ok(busRepository.findByRouteRouteNameIgnoreCase(routeName));
    }

    @GetMapping("/buses/{licensePlate}/geopoints")
    public ResponseEntity<List<GeoPoint>> getGeoPointsByLicensePlate(@PathVariable String licensePlate) {
        return ResponseEntity.ok(geoPointRepository.findByBusLicensePlateIgnoreCaseOrderByTimestampAsc(licensePlate));
    }

    @GetMapping("/buses/{licensePlate}/geopoints/latest")
    public ResponseEntity<GeoPoint> getLatestGeoPoint(@PathVariable String licensePlate) {
        return geoPointRepository
                .findFirstByBusLicensePlateIgnoreCaseOrderByTimestampDesc(licensePlate)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
