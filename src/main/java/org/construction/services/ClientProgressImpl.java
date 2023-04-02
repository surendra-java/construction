package org.construction.services;

import org.construction.dto.ClientProgressDto;
import org.construction.model.ClientProgress;
import org.construction.repo.ClientProgressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientProgressImpl implements IClientProgressService {

    private final ClientProgressRepo clientProgressRepo;

    @Autowired
    public ClientProgressImpl(ClientProgressRepo clientProgressRepo) {
        this.clientProgressRepo = clientProgressRepo;
    }
    @Transactional
    @Override
    public List<ClientProgressDto> getClientProgress(Long clientId) {
        List<ClientProgress> byClientMasterClientId = clientProgressRepo.findByClientMasterClientId(clientId);

        return byClientMasterClientId
                .stream()
                .map(clientProgress -> {
                    ClientProgressDto clientProgressDto = new ClientProgressDto();
                    clientProgressDto.setClientProgressId(clientProgress.getClientProgressId());
                    clientProgressDto.setClientProgressStatusId(clientProgress.getClientProgressStatus().getClientProgressStatusId());
                    clientProgressDto.setClientProgressMasterId(clientProgress.getClientProgressMaster().getClientProgressMasterId());
                    clientProgressDto.setClientId(clientProgress.getClientMaster().getClientId());
                    clientProgressDto.setReason(clientProgress.getReason());
                    return clientProgressDto;
                }).collect(Collectors.toList());

    }
}
