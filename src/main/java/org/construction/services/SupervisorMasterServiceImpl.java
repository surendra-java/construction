package org.construction.services;

import org.construction.dto.EngineerMasterDto;
import org.construction.dto.SupervisorMasterDto;
import org.construction.model.EngineerMaster;
import org.construction.model.SupervisorMaster;
import org.construction.repo.SupervisorMasterRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupervisorMasterServiceImpl implements ISupervisorMasterService{

    private final SupervisorMasterRepo supervisorMasterRepo;

    private final ModelMapper modelMapper;
    @Autowired
    public SupervisorMasterServiceImpl(SupervisorMasterRepo supervisorMasterRepo, ModelMapper modelMapper){
        this.supervisorMasterRepo = supervisorMasterRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createSupervisor(String supervisorName, String supervisorAddress, String supervisorMobileNumber, MultipartFile supervisorPhoto) throws IOException {
        SupervisorMaster supervisorMaster = new SupervisorMaster();
        supervisorMaster.setSupervisorName(supervisorName);
        supervisorMaster.setSupervisorAddress(supervisorAddress);
        supervisorMaster.setSupervisorMobNbr(supervisorMobileNumber);
        supervisorMaster.setSupervisorPhoto(supervisorPhoto.getBytes());
        supervisorMasterRepo.save(supervisorMaster);
    }

    @Override
    public void updateSupervisor(Long supervisorMasterId, String supervisorName, String supervisorAddress, String supervisorMobileNumber, MultipartFile supervisorPhoto) throws IOException {
        SupervisorMaster supervisorMaster = supervisorMasterRepo.findById(supervisorMasterId).get();
        supervisorMaster.setSupervisorName(supervisorName);
        supervisorMaster.setSupervisorAddress(supervisorAddress);
        supervisorMaster.setSupervisorMobNbr(supervisorMobileNumber);
        supervisorMaster.setSupervisorPhoto(supervisorPhoto.getBytes());
        supervisorMasterRepo.save(supervisorMaster);
    }

    @Override
    public SupervisorMasterDto getSupervisorInfo(Long supervisorMasterId) {
        SupervisorMaster supervisorMaster = supervisorMasterRepo.findById(supervisorMasterId).get();
        return modelMapper.map(supervisorMaster, SupervisorMasterDto.class);
    }

    @Override
    public List<SupervisorMasterDto> getSupervisorsInfo() {
            List<SupervisorMaster> supervisorMasters = supervisorMasterRepo.findAll();
            List<SupervisorMasterDto> supervisors = new ArrayList<>();
            if(supervisorMasters!=null && !supervisorMasters.isEmpty()) {
                supervisors  = supervisorMasters
                        .stream()
                        .map(supervisor -> modelMapper.map(supervisor, SupervisorMasterDto.class))
                        .collect(Collectors.toList());
            }
            return supervisors;
    }

    @Override
    public void deleteSupervisor(Long supervisorMasterId) {
        supervisorMasterRepo.deleteById(supervisorMasterId);
    }
}
