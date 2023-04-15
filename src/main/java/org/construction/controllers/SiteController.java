package org.construction.controllers;

import org.construction.dto.ClientMasterDto;
import org.construction.dto.SiteMasterDto;
import org.construction.services.IEngineerMasterService;
import org.construction.services.ISiteMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class SiteController {
    private final ISiteMasterService siteMasterService;
    @Autowired
    public SiteController(ISiteMasterService iSiteMasterService){
        this.siteMasterService = iSiteMasterService;
    }
    @PostMapping(value = "/create-site", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> createSite(
            @RequestParam("siteName") String siteName,
            @RequestParam("siteAddress") String siteAddress,
            @RequestParam("siteWard") String siteWard,
            @RequestParam("siteCity") String siteCity,
            @RequestParam("sitePin") String sitePin,
            @RequestParam("sitePhoto") MultipartFile sitePhoto) throws IOException {
        // process the data here
        siteMasterService.createSite(siteName, siteAddress, siteWard, siteCity, sitePin, sitePhoto);
        return ResponseEntity.ok().body("{\"message\": \"CREATED\"}");
    }

    @PutMapping(value = "/update-site", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> updateSite(
            @RequestParam("siteMasterId") Long siteMasterId,
            @RequestParam("siteName") String siteName,
            @RequestParam("siteAddress") String siteAddress,
            @RequestParam("siteWard") String siteWard,
            @RequestParam("siteCity") String siteCity,
            @RequestParam("sitePin") String sitePin,
            @RequestParam("sitePhoto") MultipartFile sitePhoto) throws IOException {
        // process the data here
        siteMasterService.updateSite(siteMasterId, siteName, siteAddress, siteWard, siteCity, sitePin, sitePhoto);
        return ResponseEntity.ok().body("{\"message\": \"CREATED\"}");
    }

    @DeleteMapping(value = "/site-delete")
    public ResponseEntity<String> deleteSite( @RequestParam("siteMasterId") Long siteMasterId) throws IOException {
        // process the data here
        siteMasterService.deleteSite(siteMasterId);
        return ResponseEntity.ok().body("{\"message\": \"DELETED\"}");
    }
    @GetMapping(value = "/site-info")
    public ResponseEntity<SiteMasterDto> getClient(
            @RequestParam("siteMasterId") Long siteMasterId) throws IOException {
        SiteMasterDto site = siteMasterService.getSiteInfo(siteMasterId);
        return ResponseEntity.ok().body(site);
    }
    @GetMapping(value = "/site-all-info")
    public ResponseEntity<List<SiteMasterDto>> getSites() {
        // process the data here
        List<SiteMasterDto> sitesInfo = siteMasterService.getSitesInfo();
        return ResponseEntity.ok().body(sitesInfo);
    }
}
