package org.construction.services;

import org.springframework.web.multipart.MultipartFile;

public interface ISupervisorMasterService {
    void createsupervisor(String supervisorName, String supervisorAddress, String supervisorMobileNumber, MultipartFile supervisorPhoto);
}
