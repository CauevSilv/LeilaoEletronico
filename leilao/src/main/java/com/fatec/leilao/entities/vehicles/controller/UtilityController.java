package com.fatec.leilao.entities.vehicles.controller;

import com.fatec.leilao.entities.vehicles.Utility;
import com.fatec.leilao.entities.vehicles.dto.UtilityDto;
import com.fatec.leilao.entities.vehicles.services.UtilityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/utility")
public class UtilityController {

    @Autowired
    private final UtilityService utilityService;


    public UtilityController(UtilityService utilityService){
        this.utilityService = utilityService;}

    @PostMapping("/create")
    public void createVehicleUtility(@RequestBody UtilityDto utilityDto){
        utilityService.save(utilityDto);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Optional<Utility>> getUtilityById(@PathVariable Long id){
        Optional<Utility> utilityFound = utilityService.findById(id);
        return ResponseEntity.ok(utilityFound);

    }


}
