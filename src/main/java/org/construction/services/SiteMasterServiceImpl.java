package org.construction.services;

import org.construction.dto.SiteMasterDto;
import org.construction.model.SiteMaster;
import org.construction.repo.SiteMasterRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SiteMasterServiceImpl implements ISiteMasterService{

    private final SiteMasterRepo siteMasterRepo;

    private final ModelMapper modelMapper;
    @Autowired
    public SiteMasterServiceImpl(SiteMasterRepo siteMasterRepo,  ModelMapper modelMapper) {
        this.siteMasterRepo = siteMasterRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createSite(String siteName, String siteAddress, String siteWard, String siteCity, String sitePin, MultipartFile sitePhoto) throws IOException {
        SiteMaster siteMaster = new SiteMaster();
        siteMaster.setSiteName(siteName);
        siteMaster.setSiteAddress(siteAddress);
        siteMaster.setSiteWard(siteWard);
        siteMaster.setSiteCity(siteCity);
        siteMaster.setSitePin(sitePin);
        siteMaster.setSitePhoto(sitePhoto.getBytes());
        siteMasterRepo.save(siteMaster);
    }

    @Override
    public List<SiteMasterDto> getSitesInfo() {
        List<SiteMaster> siteMasters = siteMasterRepo.findAll();
        List<SiteMasterDto> sites = new ArrayList<>();
        if(siteMasters!=null && !siteMasters.isEmpty()) {
            sites  = siteMasters
                    .stream()
                    .map(siteMaster -> modelMapper.map(siteMaster, SiteMasterDto.class))
                    .collect(Collectors.toList());
        }
        return sites;
    }

    @Override
    public void deleteSite(Long siteMasterId) {
        siteMasterRepo.deleteById(siteMasterId);
    }

    @Override
    public SiteMasterDto getSiteInfo(Long siteMasterId) {
        SiteMaster siteMaster = siteMasterRepo.findById(siteMasterId).get();
        return modelMapper.map(siteMaster, SiteMasterDto.class);
    }

    @Override
    public void updateSite(Long siteMasterId, String siteName, String siteAddress, String siteWard, String siteCity, String sitePin, MultipartFile sitePhoto) throws IOException {
        SiteMaster siteMaster = siteMasterRepo.findById(siteMasterId).get();
        siteMaster.setSiteName(siteName);
        siteMaster.setSiteAddress(siteAddress);
        siteMaster.setSiteWard(siteWard);
        siteMaster.setSiteCity(siteCity);
        siteMaster.setSitePin(sitePin);
        siteMaster.setSitePhoto(sitePhoto.getBytes());
        siteMasterRepo.save(siteMaster);
    }
}
