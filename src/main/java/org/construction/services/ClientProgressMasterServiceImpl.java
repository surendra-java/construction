package org.construction.services;

import org.construction.dto.ClientProgressMasterDto;
import org.construction.repo.ClientProgressMasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientProgressMasterServiceImpl implements IClientProgressMasterService {
    private final ClientProgressMasterRepo clientProgressMasterRepo;

    @Autowired
    public ClientProgressMasterServiceImpl(ClientProgressMasterRepo clientProgressMasterRepo) {
        this.clientProgressMasterRepo = clientProgressMasterRepo;
    }


    @Override
    public List<ClientProgressMasterDto> getAllInitialClientPhases() {
        /*return clientProgressMasterRepo.findAll()
                .stream()
                .map(progressMaster -> modelMapper.map(progressMaster, ClientProgressMasterDto.class))
                .collect(Collectors.toList());*/
        return null;
    }

  }
