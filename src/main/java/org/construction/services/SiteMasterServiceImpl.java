package org.construction.services;

import org.construction.dto.SiteMasterDto;
import org.construction.model.*;
import org.construction.repo.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SiteMasterServiceImpl implements ISiteMasterService{

    private final SiteMasterRepo siteMasterRepo;
    //private final SiteAllocationRepo siteAllocationRepo;

    private final ClientAllocationRepo clientAllocationRepo;

    private final EngineerAllocationRepo engineerAllocationRepo;

    private final SupervisorAllocationRepo supervisorAllocationRepo;

    private final ClientMasterRepo clientMasterRepo;

    private final EngineerMasterRepo engineerMasterRepo;

    private final SupervisorMasterRepo supervisorMasterRepo;
    private final ModelMapper modelMapper;
    @Autowired
    public SiteMasterServiceImpl(SiteMasterRepo siteMasterRepo,
                                 ClientAllocationRepo clientAllocationRepo,
                                 EngineerAllocationRepo engineerAllocationRepo,
                                 SupervisorAllocationRepo supervisorAllocationRepo,
                                 ClientMasterRepo clientMasterRepo,
                                 EngineerMasterRepo engineerMasterRepo,
                                 SupervisorMasterRepo supervisorMasterRepo,
                                 ModelMapper modelMapper) {
        this.siteMasterRepo = siteMasterRepo;
        this.clientAllocationRepo = clientAllocationRepo;
        this.engineerAllocationRepo = engineerAllocationRepo;
        this.supervisorAllocationRepo = supervisorAllocationRepo;
        this.clientMasterRepo = clientMasterRepo;
        this.engineerMasterRepo = engineerMasterRepo;
        this.supervisorMasterRepo = supervisorMasterRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createSite(Long client, Long engineer, Long supervisor, String siteName, String siteAddress, String siteWard, String siteCity, String sitePin, MultipartFile sitePhoto) throws IOException {
        SiteMaster siteMaster = new SiteMaster();
        siteMaster.setSiteName(siteName);
        siteMaster.setSiteAddress(siteAddress);
        siteMaster.setSiteWard(siteWard);
        siteMaster.setSiteCity(siteCity);
        siteMaster.setSitePin(sitePin);
        siteMaster.setSitePhoto(sitePhoto.getBytes());
        SiteMaster savedSiteMaster = siteMasterRepo.save(siteMaster);


        ClientAllocation clientAllocation = new ClientAllocation();
        clientAllocation.setSiteMaster(savedSiteMaster);
        ClientMaster clientMaster = clientMasterRepo.findById(client).get();
        clientAllocation.setClientMaster(clientMaster);
        clientAllocationRepo.save(clientAllocation);

        EngineerAllocation engineerAllocation = new EngineerAllocation();
        engineerAllocation.setSiteMaster(savedSiteMaster);
        EngineerMaster engineerMaster = engineerMasterRepo.findById(engineer).get();
        engineerAllocation.setEngineerMaster(engineerMaster);
        engineerAllocationRepo.save(engineerAllocation);

        SupervisorAllocation supervisorAllocation = new SupervisorAllocation();
        supervisorAllocation.setSiteMaster(savedSiteMaster);
        SupervisorMaster supervisorMaster = supervisorMasterRepo.findById(supervisor).get();
        supervisorAllocation.setSupervisorMaster(supervisorMaster);
        supervisorAllocationRepo.save(supervisorAllocation);


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

    @Transactional
    @Override
    public void deleteSite(Long siteMasterId) {
        SiteMaster siteMaster = siteMasterRepo.findById(siteMasterId).orElse(null);
        ClientAllocation clientAllocation = clientAllocationRepo.findBySiteMaster(siteMaster);
        EngineerAllocation engineerAllocation=engineerAllocationRepo.findBySiteMaster(siteMaster);
        SupervisorAllocation supervisorAllocation = supervisorAllocationRepo.findBySiteMaster(siteMaster);
        if(clientAllocation!=null)
            clientAllocationRepo.deleteById(clientAllocation.getClientAllocationId());
        if(engineerAllocation!=null)
            engineerAllocationRepo.deleteById(engineerAllocation.getEngineerAllocationId());
        if(supervisorAllocation!=null)
            supervisorAllocationRepo.deleteById(supervisorAllocation.getSupervisorAllocationId());
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
