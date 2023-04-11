package org.construction.services;

import org.construction.dto.ClientProgressStatusDto;
import org.construction.repo.ClientProgressStatusRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientProgressStatusImpl implements IClientProgressStatusService {
    private final ClientProgressStatusRepo clientProgressStatusRepo;

    private final ModelMapper modelMapper;

    @Autowired
    public ClientProgressStatusImpl(ClientProgressStatusRepo clientProgressStatusRepo, ModelMapper modelMapper) {
        this.clientProgressStatusRepo = clientProgressStatusRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ClientProgressStatusDto> getAllInitialProgressStatus() {
        return clientProgressStatusRepo.findAll()
                .stream()
                .map(clientProgressStatus -> modelMapper.map(clientProgressStatus, ClientProgressStatusDto.class))
                .collect(Collectors.toList());
    }
}
