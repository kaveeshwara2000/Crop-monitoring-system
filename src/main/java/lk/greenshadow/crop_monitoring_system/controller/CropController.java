package lk.greenshadow.crop_monitoring_system.controller;

import lk.greenshadow.crop_monitoring_system.model.Crop;
import lk.greenshadow.crop_monitoring_system.service.CropRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/crop")
public class CropController {

    @Autowired
    private CropRepo cropRepo;

    @GetMapping({"","/"})
    public String showCropList(Model model){
        List<Crop> crop = cropRepo.findAll();
        model.addAttribute("crop",crop);
        return "crop/index";
    }
}
