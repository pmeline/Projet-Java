package com.oxyl.coursepfback.CONTROLLER;

import com.oxyl.coursepfback.DTO.PlanteDTOMapper;
import com.oxyl.coursepfback.MODEL.PlanteModel;
import com.oxyl.coursepfback.SERVICE.PlanteServiceInterface;
import com.oxyl.coursepfback.DTO.PlanteDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plantes")
public class PlanteController {

    private final PlanteServiceInterface planteService;
    private final PlanteDTOMapper planteDTOMapper;

    public PlanteController(PlanteServiceInterface planteService, PlanteDTOMapper planteDTOMapper) {
        this.planteService = planteService;
        this.planteDTOMapper = planteDTOMapper;
    }

    @GetMapping
    public List<PlanteDTO> getAllPlantes() {
        List<PlanteModel> plantes = planteService.getAllPlantes();
        return planteDTOMapper.mapListModelsToDTO(plantes);
    }

    @GetMapping("/{id_plante}")
    public ResponseEntity<PlanteDTO> getPlanteById(@PathVariable Long id_plante) {
        PlanteModel plante = planteService.getPlante(id_plante);
        if (plante == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(planteDTOMapper.mapModelToDTO(plante), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createPlante(@Validated @RequestBody PlanteDTO planteDTO) {
        PlanteModel planteModel = planteDTOMapper.mapDTOToModel(planteDTO);
        planteService.createPlante(planteModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id_plante}")
    public ResponseEntity<Void> updatePlante(@PathVariable Long id_plante, @Validated @RequestBody PlanteDTO planteDTO) {
        if (!id_plante.equals(planteDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PlanteModel planteModel = planteDTOMapper.mapDTOToModel(planteDTO);
        planteService.updatePlante(planteModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id_plante}")
    public ResponseEntity<Void> deletePlante(@PathVariable Long id_plante) {
        planteService.deletePlante(id_plante);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
