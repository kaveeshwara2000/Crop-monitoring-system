package lk.greenshadow.crop_monitoring_system.controller;

import jakarta.validation.Valid;
import lk.greenshadow.crop_monitoring_system.dto.CropDTO;
import lk.greenshadow.crop_monitoring_system.model.Crop;
import lk.greenshadow.crop_monitoring_system.service.CropRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.*;
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

        Crop crop = new Crop();
        crop.setCode(cropDTO.getCode());
        crop.setcName(cropDTO.getcName());
        crop.setsName(cropDTO.getsName());
        crop.setImage(storageFileName);
        crop.setCategory(cropDTO.getCategory());
        crop.setSeason(cropDTO.getSeason());
        crop.setField(cropDTO.getField());

        cropRepo.save(crop);

        return "redirect:/crop";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam String code){
        try {
            Crop crop = cropRepo.findById(code).get();
            model.addAttribute("crop",crop);

            CropDTO cropDTO = new CropDTO();
            cropDTO.setcName(crop.getcName());
            cropDTO.setsName(crop.getsName());
            cropDTO.setCategory(crop.getCategory());
            cropDTO.setSeason(crop.getSeason());
            cropDTO.setField(crop.getField());

            model.addAttribute("cropDTO",cropDTO);

        } catch (Exception exception) {
            exception.printStackTrace();
            return "redirect:/crop";
        }
        return "crop/editCrop";
    }

    @PostMapping("/edit")
    public String updateCrop(Model model,@RequestParam String code,@Valid @ModelAttribute CropDTO cropDTO,BindingResult result){

        try {
            Crop crop = cropRepo.findById(code).get();
            model.addAttribute("crop",crop);

            if (result.hasErrors()){
                return "crop/editCrop";
            }
            if (!cropDTO.getImage().isEmpty()){
                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir+crop.getImage());
                try {
                    Files.delete(oldImagePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MultipartFile image = cropDTO.getImage();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime()+"_"+image.getOriginalFilename();
                try(InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream,Paths.get(uploadDir+storageFileName),StandardCopyOption.REPLACE_EXISTING);
                }
                crop.setImage(storageFileName);
            }
            crop.setcName(cropDTO.getcName());
            crop.setsName(cropDTO.getsName());
            crop.setCategory(cropDTO.getCategory());
            crop.setSeason(cropDTO.getSeason());
            crop.setField(cropDTO.getField());

            cropRepo.save(crop);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return "redirect:/crop";
    }



    @GetMapping("/delete")
    public String deleteCrop(@RequestParam String code){

        try {
            Crop crop = cropRepo.findById(code).get();
            Path imagePath = Paths.get("public/images/"+crop.getImage());

            try {
                Files.delete(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            cropRepo.delete(crop);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return "redirect:/crop";
    }

}
