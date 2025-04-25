package com.oxyl.coursepfback.controller;

import com.oxyl.coursepfback.dto.PlanteDTO;
import com.oxyl.coursepfback.dto.PlanteDTOMapper;
import com.oxyl.coursepfback.model.PlanteModel;
import com.oxyl.coursepfback.service.PlanteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plantes")
public class PlanteController {

    private final PlanteService planteService;
    private final PlanteDTOMapper mapper;

    public PlanteController(PlanteService planteService, PlanteDTOMapper mapper) {
        this.planteService = planteService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<PlanteDTO> getAllPlantes() {
        List<PlanteModel> plantes = planteService.getAllPlantes();
        return mapper.mapListModelsToDTO(plantes);
    }

    @GetMapping("/{id_plante}")
    public ResponseEntity<PlanteDTO> getPlanteById(@PathVariable ("id_plante") Long id_plante) {
        PlanteModel plante = planteService.getPlante(id_plante);
        if (plante == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mapper.mapModelToDTO(plante), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlanteDTO> createPlante(@RequestBody PlanteDTO planteDTO) {
        PlanteModel plante = mapper.mapDTOToModel(planteDTO);
        planteService.createPlante(plante);
        return new ResponseEntity<>(mapper.mapModelToDTO(plante), HttpStatus.CREATED);
    }

    @PutMapping("/{id_plante}")
    public ResponseEntity<PlanteDTO> updatePlante(@PathVariable ("id_plante") Long id_plante, @RequestBody PlanteDTO planteDTO) {
        PlanteModel plante = mapper.mapDTOToModel(planteDTO);
        plante.setId_plante(id_plante);
        planteService.updatePlante(plante);
        return new ResponseEntity<>(mapper.mapModelToDTO(plante), HttpStatus.OK);
    }

    @DeleteMapping("/{id_plante}")
    public ResponseEntity<Object> deletePlante(@PathVariable ("id_plante") Long id_plante) {
        try {
            planteService.deletePlante(id_plante);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
