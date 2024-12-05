package lk.greenshadow.crop_monitoring_system.service;

import lk.greenshadow.crop_monitoring_system.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepo extends JpaRepository<Equipment,String> {
}
