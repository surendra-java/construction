package org.construction.controllers;

import org.construction.dto.ClientMasterDto;
import org.construction.services.IClientMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ClientController {

    private final IClientMasterService clientMasterService;
    @Autowired
    public ClientController(IClientMasterService iClientMasterService){
        this.clientMasterService = iClientMasterService;
    }

    @PostMapping(value = "/create-client", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> createClient(
            @RequestParam("clientName") String clientName,
            @RequestParam("clientAddress") String clientAddress,
            @RequestParam("clientMobileNumber") String clientMobileNumber,
            @RequestParam("clientPhoto") MultipartFile clientPhoto) throws IOException {
        System.out.println(clientName + " "+ clientAddress+" "+clientMobileNumber);
        // process the data here
        clientMasterService.createClient(clientName, clientAddress, clientMobileNumber, clientPhoto);
        return ResponseEntity.ok().body("{\"message\": \"CREATED\"}");
    }

    @PutMapping(value = "/update-client", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> updateClient(

            @RequestParam("clientId") Long clientId,
            @RequestParam("clientName") String clientName,
            @RequestParam("clientAddress") String clientAddress,
            @RequestParam("clientMobileNumber") String clientMobileNumber,
            @RequestParam("clientProgressMasterId") Long clientProgressMasterId,
            @RequestParam("clientProgressStatusId") Long clientProgressStatusId,
            @RequestParam("clientPhoto") MultipartFile clientPhoto) throws IOException {

        clientMasterService.updateClient(clientId, clientName, clientAddress, clientMobileNumber, clientPhoto, clientProgressMasterId, clientProgressStatusId);
        return ResponseEntity.ok().body("{\"message\": \"CREATED\"}");
    }

    @GetMapping(value = "/client-info")
    public ResponseEntity<ClientMasterDto> getClient(
            @RequestParam("clientId") Long clientId) throws IOException {
        ClientMasterDto clientInfo = clientMasterService.getClientInfo(clientId);
        return ResponseEntity.ok().body(clientInfo);
    }



    @GetMapping(value = "/client-all-info")
    public ResponseEntity<List<ClientMasterDto>> getClients() {
        // process the data here
        List<ClientMasterDto> clientsInfo = clientMasterService.getClientsInfo();
        return ResponseEntity.ok().body(clientsInfo);
    }

    @GetMapping(value = "/unallocated-client-all-info")
    public ResponseEntity<List<ClientMasterDto>> getUnallocatedClients() {
        // process the data here
        List<ClientMasterDto> clientsInfo = clientMasterService.getUnallocatedClientsInfo();
        return ResponseEntity.ok().body(clientsInfo);
    }

    @DeleteMapping(value = "/client-delete")
    public ResponseEntity<String> deleteClient( @RequestParam("clientId") Long clientId) throws IOException {
        // process the data here
        clientMasterService.deleteClient(clientId);
        return ResponseEntity.ok().body("{\"message\": \"DELETED\"}");
    }





   /* @GetMapping("/client-photo")
    public ResponseEntity<byte[]> getClientPhoto() {
        ClientMaster client = clientMasterRepo.findById(4L).orElse(null);
        if (client == null || client.getClientPhoto() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(client.getClientPhoto());
    }*/
}
