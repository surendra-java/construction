package org.construction.controllers;

import org.construction.dto.EngineerMasterDto;
import org.construction.services.IEngineerAllocationService;
import org.construction.services.IEngineerMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EngineerAllocationController {
    private final IEngineerAllocationService engineerAllocationService;
    @Autowired
    public EngineerAllocationController(IEngineerAllocationService iEngineerAllocationService){
        this.engineerAllocationService = iEngineerAllocationService;
    }
    @GetMapping(value = "/allocated-engineer-info")
    public ResponseEntity<EngineerMasterDto> getAllocatedEngineers(@RequestParam("siteMasterId") Long siteMasterId) {
        // process the data here
        EngineerMasterDto engineersInfo = engineerAllocationService.getAllocatedEngineerInfo(siteMasterId);
        return ResponseEntity.ok().body(engineersInfo);
    }
}
