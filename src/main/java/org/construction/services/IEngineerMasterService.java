package org.construction.services;

import org.springframework.web.multipart.MultipartFile;

public interface IEngineerMasterService {
    void createEngineer(String engineerName, String engineerAddress, String engineerMobileNumber, MultipartFile engineerPhoto);
}
