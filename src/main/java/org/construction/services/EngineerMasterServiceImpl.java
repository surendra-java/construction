package org.construction.services;

import org.construction.dto.EngineerMasterDto;
import org.construction.dto.SiteMasterDto;
import org.construction.model.EngineerMaster;
import org.construction.model.SiteMaster;
import org.construction.repo.EngineerMasterRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EngineerMasterServiceImpl implements IEngineerMasterService{

    private final EngineerMasterRepo engineerMasterRepo;

    private final ModelMapper modelMapper;
    @Autowired
    public EngineerMasterServiceImpl(EngineerMasterRepo engineerMasterRepo, ModelMapper modelMapper) {
        this.engineerMasterRepo = engineerMasterRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createEngineer(String engineerName, String engineerAddress, String engineerMobileNumber, MultipartFile engineerPhoto) throws IOException {
        EngineerMaster engineerMaster = new EngineerMaster();
        engineerMaster.setEngineerName(engineerName);
        engineerMaster.setEngineerAddress(engineerAddress);
        engineerMaster.setEngineerMobNbr(engineerMobileNumber);
        engineerMaster.setEngineerPhoto(engineerPhoto.getBytes());
        engineerMasterRepo.save(engineerMaster);
    }

    @Override
    public void updateEngineer(Long engineerMasterId, String engineerName, String engineerAddress, String engineerMobileNumber, MultipartFile engineerPhoto) throws IOException {
        EngineerMaster engineerMaster = engineerMasterRepo.findById(engineerMasterId).get();
        engineerMaster.setEngineerName(engineerName);
        engineerMaster.setEngineerAddress(engineerAddress);
        engineerMaster.setEngineerMobNbr(engineerMobileNumber);
        engineerMaster.setEngineerPhoto(engineerPhoto.getBytes());
        engineerMasterRepo.save(engineerMaster);
    }

    @Override
    public void deleteEngineer(Long engineerMasterId) {
        engineerMasterRepo.deleteById(engineerMasterId);
    }

    @Override
    public EngineerMasterDto getEngineerInfo(Long engineerMasterId) {
        EngineerMaster engineerMaster = engineerMasterRepo.findById(engineerMasterId).get();
        return modelMapper.map(engineerMaster, EngineerMasterDto.class);
    }

    @Override
    public List<EngineerMasterDto> getEngineersInfo() {
        List<EngineerMaster> engineerMasters = engineerMasterRepo.findAll();
        List<EngineerMasterDto> engineers = new ArrayList<>();
        if(engineerMasters!=null && !engineerMasters.isEmpty()) {
            engineers  = engineerMasters
                    .stream()
                    .map(engineer -> modelMapper.map(engineer, EngineerMasterDto.class))
                    .collect(Collectors.toList());
        }
        return engineers;
    }
}
