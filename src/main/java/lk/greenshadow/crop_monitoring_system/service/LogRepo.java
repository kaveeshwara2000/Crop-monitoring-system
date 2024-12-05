package lk.greenshadow.crop_monitoring_system.service;

import lk.greenshadow.crop_monitoring_system.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepo extends JpaRepository<Log,String> {
}
