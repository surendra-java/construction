package org.construction.services;

import org.construction.dto.ClientMasterDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IClientMasterService {
    void createClient(String clientName,
                      String clientAddress,
                      String clientMobileNumber,
                      MultipartFile clientPhoto) throws IOException;

    ClientMasterDto getClientInfo(Long clientId);

    List<ClientMasterDto> getClientsInfo();

    List<ClientMasterDto> getUnallocatedClientsInfo();

    void deleteClient(Long clientId);

    void updateClient(Long clientId,
                      String clientName,
                      String clientAddress,
                      String clientMobileNumber,
                      MultipartFile clientPhoto,
                      Long clientProgressMasterId,
                      Long clientProgressStatusId) throws IOException;


}
