package org.construction.controllers;

import org.construction.services.IEngineerMasterService;
import org.construction.services.ISupervisorMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
        supervisorMasterService.createsupervisor(supervisorName, supervisorAddress, supervisorMobileNumber, supervisorPhoto);
        return ResponseEntity.ok().body("{\"message\": \"CREATED\"}");
    }
}
