package lk.greenshadow.crop_monitoring_system.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String password;
    private String role;
}
