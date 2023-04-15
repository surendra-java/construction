package org.construction.controllers;

import org.construction.services.IClientMasterService;
import org.construction.services.IEngineerMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class EngineerController {
    private final IEngineerMasterService engineerMasterService;
    @Autowired
    public EngineerController(IEngineerMasterService iEngineerMasterService){
        this.engineerMasterService = iEngineerMasterService;
    }
    @PostMapping(value = "/create-engineer", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> createEngineer(
            @RequestParam("engineerName") String engineerName,
            @RequestParam("engineerAddress") String engineerAddress,
            @RequestParam("engineerMobileNumber") String engineerMobileNumber,
            @RequestParam("engineerPhoto") MultipartFile engineerPhoto) throws IOException {
        System.out.println(engineerName + " "+ engineerAddress+" "+engineerMobileNumber);
        // process the data here
        engineerMasterService.createEngineer(engineerName, engineerAddress, engineerMobileNumber, engineerPhoto);
        return ResponseEntity.ok().body("{\"message\": \"CREATED\"}");
    }
}
