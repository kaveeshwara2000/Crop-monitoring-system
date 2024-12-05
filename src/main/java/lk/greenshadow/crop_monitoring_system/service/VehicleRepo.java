package lk.greenshadow.crop_monitoring_system.service;

import lk.greenshadow.crop_monitoring_system.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository<Vehicle,String> {
}
