package org.construction.services;

import org.construction.dto.ClientProgressMasterDto;
import org.construction.repo.ClientProgressMasterRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientProgressMasterServiceImpl implements IClientProgressMasterService {
    private final ClientProgressMasterRepo clientProgressMasterRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientProgressMasterServiceImpl(ClientProgressMasterRepo clientProgressMasterRepo, ModelMapper modelMapper) {
        this.clientProgressMasterRepo = clientProgressMasterRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ClientProgressMasterDto> getAllInitialClientPhases() {
        return clientProgressMasterRepo.findAll()
                .stream()
                .map(progressMaster -> modelMapper.map(progressMaster, ClientProgressMasterDto.class))
                .collect(Collectors.toList());
    }

  }
