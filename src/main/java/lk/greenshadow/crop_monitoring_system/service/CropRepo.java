package lk.greenshadow.crop_monitoring_system.service;

import lk.greenshadow.crop_monitoring_system.model.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRepo extends JpaRepository<Crop,String> {
}
