package org.construction.services;

import org.construction.dto.EngineerMasterDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IEngineerMasterService {
    void createEngineer(String engineerName, String engineerAddress, String engineerMobileNumber, MultipartFile engineerPhoto) throws IOException;

    void updateEngineer(Long engineerMasterId, String engineerName, String engineerAddress, String engineerMobileNumber, MultipartFile engineerPhoto) throws IOException;

    void deleteEngineer(Long engineerMasterId);

    EngineerMasterDto getEngineerInfo(Long engineerMasterId);

    List<EngineerMasterDto> getEngineersInfo();

}
