package org.construction.controllers;

import org.construction.dto.ClientProgressDto;
import org.construction.dto.ClientProgressStatusDto;
import org.construction.dto.ClientProgressMasterDto;
import org.construction.services.IClientProgressService;
import org.construction.services.IClientProgressStatusService;
import org.construction.services.IClientProgressMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientProgressController {

    @Autowired
    IClientProgressStatusService iIntlPrgsLuService;

    @Autowired
    IClientProgressMasterService iIntlPrgsMasterService;

    @Autowired
    IClientProgressService iClientProgressService;



    @GetMapping(value = "/all-client--status")
    public ResponseEntity<List<ClientProgressStatusDto>> getAllClientInitialStatus() {
        // process the data here
        List<ClientProgressStatusDto> allInitialProgressLuDto = iIntlPrgsLuService.getAllInitialProgressStatus();
        return ResponseEntity.ok().body(allInitialProgressLuDto);
    }

    @GetMapping(value = "/all-client-progress")
    public ResponseEntity<List<ClientProgressMasterDto>> getAllClientInitialPhases() {
        // process the data here
        List<ClientProgressMasterDto> allInitialClientPhases = iIntlPrgsMasterService.getAllInitialClientPhases();
        return ResponseEntity.ok().body(allInitialClientPhases);
    }

    @GetMapping(value = "/client-progress")
    public ResponseEntity<List<ClientProgressDto>> getClientProgress(@RequestParam("clientId") Long clientId) {
        // process the data here
        List<ClientProgressDto> clientProgress = iClientProgressService.getClientProgress(clientId);
        return ResponseEntity.ok().body(clientProgress);
    }
}
