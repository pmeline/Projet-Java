package com.oxyl.coursepfback.controller;

import com.oxyl.coursepfback.dto.PlanteDTOMapper;
import com.oxyl.coursepfback.model.PlanteModel;
import com.oxyl.coursepfback.service.PlanteService;
import com.oxyl.coursepfback.dto.PlanteDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plantes")
public class PlanteController {

    private final PlanteService planteService;
    private final PlanteDTOMapper planteDTOMapper;

    @Autowired
    public PlanteController(PlanteService planteService, PlanteDTOMapper planteDTOMapper) {
        this.planteService = planteService;
        this.planteDTOMapper = planteDTOMapper;
    }

    @GetMapping
    public List<PlanteDTO> getAllPlantes() {
        List<PlanteModel> plantes = planteService.getAllPlantes();
        return planteDTOMapper.mapListModelsToDTO(plantes);
    }

    @GetMapping("/{id_plante}")
    public ResponseEntity<PlanteDTO> getPlanteById(@PathVariable ("id_plante") Long id_plante) {
        PlanteModel plante = planteService.getPlante(id_plante);
        if (plante == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(planteDTOMapper.mapModelToDTO(plante), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createPlante( @RequestBody PlanteDTO planteDTO) {
        PlanteModel planteModel = planteDTOMapper.mapDTOToModel(planteDTO);
        planteService.createPlante(planteModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id_plante}")
    public ResponseEntity<Void> updatePlante(@PathVariable ("id_plante") Long id_plante,  @RequestBody PlanteDTO planteDTO) {
        PlanteModel planteModel = planteDTOMapper.mapDTOToModel(planteDTO);
        planteService.updatePlante(planteModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id_plante}")
    public ResponseEntity<Void> deletePlante(@PathVariable ("id_plante") Long id_plante) {
        planteService.deletePlante(id_plante);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
