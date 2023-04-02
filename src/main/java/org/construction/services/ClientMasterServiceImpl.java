package org.construction.services;

import org.construction.dto.ClientMasterDto;
import org.construction.model.ClientMaster;
import org.construction.model.ClientProgress;
import org.construction.model.ClientProgressMaster;
import org.construction.model.ClientProgressStatus;
import org.construction.repo.ClientMasterRepo;
import org.construction.repo.ClientProgressMasterRepo;
import org.construction.repo.ClientProgressRepo;
import org.construction.repo.ClientProgressStatusRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientMasterServiceImpl implements IClientMasterService {

    private final ClientMasterRepo clientMasterRepo;
    private final ModelMapper modelMapper;
    private final ClientProgressRepo clientProgressRepo;
    private final ClientProgressMasterRepo clientProgressMasterRepo;
    private final ClientProgressStatusRepo clientProgressStatusRepo;

    @Autowired
    public ClientMasterServiceImpl(ClientMasterRepo clientMasterRepo,
                                   ModelMapper modelMapper,
                                   ClientProgressRepo clientProgressRepo,
                                   ClientProgressMasterRepo clientProgressMasterRepo,
                                   ClientProgressStatusRepo clientProgressStatusRepo) {
        this.clientMasterRepo = clientMasterRepo;
        this.modelMapper = modelMapper;
        this.clientProgressRepo = clientProgressRepo;
        this.clientProgressMasterRepo = clientProgressMasterRepo;
        this.clientProgressStatusRepo = clientProgressStatusRepo;
    }

    @Transactional
    @Override
    public void createClient(String clientName,
                             String clientAddress,
                             String clientMobileNumber,
                             MultipartFile clientPhoto) throws IOException {
        ClientMaster clientMaster = new ClientMaster();
        clientMaster.setClientName(clientName);
        clientMaster.setClientAddress(clientAddress);
        clientMaster.setClientMobNbr(clientMobileNumber);

        byte[] bytes = clientPhoto.getBytes();
        clientMaster.setClientPhoto(bytes);
        clientMasterRepo.save(clientMaster);
    }

    @Transactional
    @Override
    public ClientMasterDto getClientInfo(Long clientId) {
        ClientMaster clientMaster = clientMasterRepo.findById(clientId).get();
        List<ClientProgress>clientProgresses=clientProgressRepo.findByClientMaster(clientMaster);
        Collections.sort(clientProgresses, Comparator.comparing(ClientProgress::getClientProgressId).reversed());
        ClientMasterDto clientMasterDto = new ClientMasterDto();
        clientMasterDto.setClientId(clientMaster.getClientId());
        clientMasterDto.setClientName(clientMaster.getClientName());
        clientMasterDto.setClientMobNbr(clientMaster.getClientMobNbr());
        clientMasterDto.setClientAddress(clientMaster.getClientAddress());
        clientMasterDto.setClientPhoto(clientMaster.getClientPhoto());

        if (!clientProgresses.isEmpty()) {
            ClientProgress latestClientProgress = clientProgresses.get(0);
            long latestClientProgressStatusId = latestClientProgress.getClientProgressMaster().getClientProgressMasterId();
            clientMasterDto.setClientProgressMasterId(latestClientProgressStatusId);
            long clientProgressStatusId = latestClientProgress.getClientProgressStatus().getClientProgressStatusId();
            clientMasterDto.setClientProgressStatusId(clientProgressStatusId);
        }
        return clientMasterDto;
    }



    @Transactional
    @Override
    public List<ClientMasterDto> getClientsInfo() {
        List<ClientMaster> clientsMaster = clientMasterRepo.findAll();
        return clientsMaster
                .stream()
                .map(clientMaster -> modelMapper.map(clientMaster, ClientMasterDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteClient(Long clientId) {
        Optional<ClientMaster> optionalClientMaster = clientMasterRepo.findById(clientId);
        if(!optionalClientMaster.isPresent()){
            //error msg
        }
        ClientMaster clientMaster = optionalClientMaster.get();
        List<ClientProgress>clientProgresses=clientProgressRepo.findByClientMaster(clientMaster);
        clientProgresses.forEach(clientProgress -> clientProgressRepo.delete(clientProgress));
        clientMasterRepo.delete(clientMaster);
    }

    @Transactional
    @Override
    public void updateClient(Long clientId,
                             String clientName,
                             String clientAddress,
                             String clientMobileNumber,
                             MultipartFile clientPhoto,
                             Long clientProgressMasterId,
                             Long clientProgressStatusId) throws IOException {

        ClientMaster clientMaster = clientMasterRepo.findById(clientId).get();
        clientMaster.setClientName(clientName);
        clientMaster.setClientAddress(clientAddress);
        clientMaster.setClientMobNbr(clientMobileNumber);
        clientMaster.setClientPhoto(clientPhoto.getBytes());
        clientMaster = clientMasterRepo.save(clientMaster);

        boolean exists = clientProgressRepo.existsByClientMasterAndClientProgressMasterAndClientProgressStatus(
                clientMaster,
                clientProgressMasterRepo.findById(clientProgressMasterId).orElse(null),
                clientProgressStatusRepo.findById(clientProgressStatusId).orElse(null)
        );
        if (!exists) {
            ClientProgress clientProgress = new ClientProgress();
            ClientProgressMaster clientProgressMaster = clientProgressMasterRepo.findById(clientProgressMasterId).get();
            clientProgress.setClientMaster(clientMaster);
            clientProgress.setClientProgressMaster(clientProgressMaster);
            ClientProgressStatus clientProgressStatus = clientProgressStatusRepo.findById(clientProgressStatusId).get();
            clientProgress.setClientProgressStatus(clientProgressStatus);
            clientProgressRepo.save(clientProgress);
        }
    }
}
