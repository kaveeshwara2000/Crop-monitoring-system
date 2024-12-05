package lk.greenshadow.crop_monitoring_system.service;

import lk.greenshadow.crop_monitoring_system.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepo extends JpaRepository<Field,String> {
}
