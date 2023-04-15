package org.construction.services;

import org.construction.repo.SupervisorMasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SupervisorMasterServiceImpl implements ISupervisorMasterService{

    private final SupervisorMasterRepo supervisorMasterRepo;

    @Autowired
    public SupervisorMasterServiceImpl(SupervisorMasterRepo supervisorMasterRepo) {
        this.supervisorMasterRepo = supervisorMasterRepo;
    }

    @Override
    public void createsupervisor(String supervisorName, String supervisorAddress, String supervisorMobileNumber, MultipartFile supervisorPhoto) {

    }
}
