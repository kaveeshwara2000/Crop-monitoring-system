package lk.greenshadow.crop_monitoring_system.controller;

import jakarta.validation.Valid;
import lk.greenshadow.crop_monitoring_system.dto.CropDTO;
import lk.greenshadow.crop_monitoring_system.model.Crop;
import lk.greenshadow.crop_monitoring_system.service.CropRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
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

    @GetMapping("/create")
    public String showCreatePage(Model model){
        CropDTO cropDTO = new CropDTO();
        model.addAttribute("cropDTO",cropDTO);
        return "crop/createCrop";
    }

    @PostMapping("/create")
    public String createCrop(@Valid @ModelAttribute CropDTO cropDTO, BindingResult result){
        if (result.hasErrors()){
            return "crop/createCrop";
        }

        MultipartFile image = cropDTO.getImage();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime()+"_"+image.getOriginalFilename();
        try{
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            try(InputStream inputStream = image.getInputStream()){
                Files.copy(inputStream,Paths.get(uploadDir+storageFileName), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/crop";
    }


}
