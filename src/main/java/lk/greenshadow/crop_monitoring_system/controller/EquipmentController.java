package lk.greenshadow.crop_monitoring_system.controller;

import jakarta.validation.Valid;
import lk.greenshadow.crop_monitoring_system.dto.CropDTO;
import lk.greenshadow.crop_monitoring_system.dto.EquipmentDTO;
import lk.greenshadow.crop_monitoring_system.model.Crop;
import lk.greenshadow.crop_monitoring_system.model.Equipment;
import lk.greenshadow.crop_monitoring_system.service.EquipmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentRepo equipmentRepo;

    @GetMapping({"","/"})
    public String showEquipmentList(Model model){
        List<Equipment> equipment = equipmentRepo.findAll();
        model.addAttribute("equipment",equipment);
        return "equipment/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model){
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        model.addAttribute("equipmentDTO",equipmentDTO);
        return "equipment/createEquipment";
    }

    @PostMapping("/create")
    public String createEquipment(@Valid @ModelAttribute EquipmentDTO equipmentDTO, BindingResult result){
        if (result.hasErrors()){
            return "crop/createCrop";
        }


        Equipment equipment = new Equipment();
        equipment.setId(equipmentDTO.getId());
        equipment.setName(equipmentDTO.getName());
        equipment.setType(equipmentDTO.getType());
        equipment.setStatus(equipmentDTO.getStatus());
        equipment.setField(equipmentDTO.getField());

        equipmentRepo.save(equipment);

        return "redirect:/crop";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam String id){
        try {
            Equipment equipment = equipmentRepo.findById(id).get();
            model.addAttribute("equipment",equipment);

            EquipmentDTO equipmentDTO = new EquipmentDTO();
            equipmentDTO.setName(equipment.getName());
            equipmentDTO.setType(equipment.getType());
            equipmentDTO.setStatus(equipment.getStatus());
            equipmentDTO.setField(equipment.getField());

            model.addAttribute("equipmentDTO",equipmentDTO);

        } catch (Exception exception) {
            exception.printStackTrace();
            return "redirect:/equipment";
        }
        return "equipment/editEquipment";
    }

}
