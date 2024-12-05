package lk.greenshadow.crop_monitoring_system.service;

import lk.greenshadow.crop_monitoring_system.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Staff,String> {

}
