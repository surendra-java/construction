package org.construction.controllers;

import org.construction.dto.EngineerMasterDto;
import org.construction.services.IEngineerMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @PutMapping(value = "/update-engineer", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> updateEngineer(
            @RequestParam("engineerMasterId") Long engineerMasterId,
            @RequestParam("engineerName") String engineerName,
            @RequestParam("engineerAddress") String engineerAddress,
            @RequestParam("engineerMobileNumber") String engineerMobileNumber,
            @RequestParam("engineerPhoto") MultipartFile engineerPhoto) throws IOException {
        // process the data here
        engineerMasterService.updateEngineer(engineerMasterId, engineerName, engineerAddress, engineerMobileNumber, engineerPhoto);
        return ResponseEntity.ok().body("{\"message\": \"CREATED\"}");
    }

    @DeleteMapping(value = "/engineer-delete")
    public ResponseEntity<String> deleteEngineer( @RequestParam("engineerMasterId") Long engineerMasterId) throws IOException {
        // process the data here
        engineerMasterService.deleteEngineer(engineerMasterId);
        return ResponseEntity.ok().body("{\"message\": \"DELETED\"}");
    }
    @GetMapping(value = "/engineer-info")
    public ResponseEntity<EngineerMasterDto> getEngineer(
            @RequestParam("engineerMasterId") Long engineerMasterId) throws IOException {
        EngineerMasterDto site = engineerMasterService.getEngineerInfo(engineerMasterId);
        return ResponseEntity.ok().body(site);
    }
    @GetMapping(value = "/engineer-all-info")
    public ResponseEntity<List<EngineerMasterDto>> getEngineers() {
        // process the data here
        List<EngineerMasterDto> engineersInfo = engineerMasterService.getEngineersInfo();
        return ResponseEntity.ok().body(engineersInfo);
    }
}
