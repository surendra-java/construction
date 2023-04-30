package org.construction.services;

import org.construction.dto.SupervisorMasterDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ISupervisorMasterService {
    void createSupervisor(String supervisorName, String supervisorAddress, String supervisorMobileNumber, MultipartFile supervisorPhoto) throws IOException;

    void updateSupervisor(Long supervisorMasterId, String supervisorName, String supervisorAddress, String supervisorMobileNumber, MultipartFile supervisorPhoto) throws IOException;

    SupervisorMasterDto getSupervisorInfo(Long supervisorMasterId);

    List<SupervisorMasterDto> getSupervisorsInfo();


    void deleteSupervisor(Long supervisorMasterId);
}
