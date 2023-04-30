package org.construction.controllers;

import org.construction.dto.EngineerMasterDto;
import org.construction.dto.SupervisorMasterDto;
import org.construction.services.ISupervisorMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class SupervisorController {

    private final ISupervisorMasterService supervisorMasterService;
    @Autowired
    public SupervisorController(ISupervisorMasterService iSupervisorMasterService){
        this.supervisorMasterService = iSupervisorMasterService;
    }
    @PostMapping(value = "/create-supervisor", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> createEngineer(
            @RequestParam("supervisorName") String supervisorName,
            @RequestParam("supervisorAddress") String supervisorAddress,
            @RequestParam("supervisorMobileNumber") String supervisorMobileNumber,
            @RequestParam("supervisorPhoto") MultipartFile supervisorPhoto) throws IOException {
        System.out.println(supervisorName + " "+ supervisorAddress+" "+supervisorMobileNumber);
        // process the data here
        supervisorMasterService.createSupervisor(supervisorName, supervisorAddress, supervisorMobileNumber, supervisorPhoto);
        return ResponseEntity.ok().body("{\"message\": \"CREATED\"}");
    }

    @PutMapping(value = "/update-supervisor", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> updateSupervisor(
            @RequestParam("supervisorMasterId") Long supervisorMasterId,
            @RequestParam("supervisorName") String supervisorName,
            @RequestParam("supervisorAddress") String supervisorAddress,
            @RequestParam("supervisorMobileNumber") String supervisorMobileNumber,
            @RequestParam("supervisorPhoto") MultipartFile supervisorPhoto) throws IOException {
        // process the data here
        supervisorMasterService.updateSupervisor(supervisorMasterId, supervisorName, supervisorAddress, supervisorMobileNumber, supervisorPhoto);
        return ResponseEntity.ok().body("{\"message\": \"CREATED\"}");
    }

    @GetMapping(value = "/supervisor-info")
    public ResponseEntity<SupervisorMasterDto> getSupervisor(
            @RequestParam("supervisorMasterId") Long supervisorMasterId) throws IOException {
        SupervisorMasterDto supervisor = supervisorMasterService.getSupervisorInfo(supervisorMasterId);
        return ResponseEntity.ok().body(supervisor);
    }

    @GetMapping(value = "/supervisor-all-info")
    public ResponseEntity<List<SupervisorMasterDto>> getSupervisors() {
        // process the data here
        List<SupervisorMasterDto> supervisorsInfo = supervisorMasterService.getSupervisorsInfo();
        return ResponseEntity.ok().body(supervisorsInfo);
    }

    @DeleteMapping(value = "/supervisor-delete")
    public ResponseEntity<String> deleteSupervisor( @RequestParam("supervisorMasterId") Long supervisorMasterId) throws IOException {
        // process the data here
        supervisorMasterService.deleteSupervisor(supervisorMasterId);
        return ResponseEntity.ok().body("{\"message\": \"DELETED\"}");
    }
}
