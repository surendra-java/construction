package org.construction.services;

import org.construction.repo.EngineerMasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EngineerMasterServiceImpl implements IEngineerMasterService{

    private final EngineerMasterRepo engineerMasterRepo;

    @Autowired
    public EngineerMasterServiceImpl(EngineerMasterRepo engineerMasterRepo) {
        this.engineerMasterRepo = engineerMasterRepo;
    }

    @Override
    public void createEngineer(String engineerName, String engineerAddress, String engineerMobileNumber, MultipartFile engineerPhoto) {

    }
}
