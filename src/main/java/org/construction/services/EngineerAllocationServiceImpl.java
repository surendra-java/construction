package org.construction.services;

import org.construction.dto.EngineerMasterDto;
import org.construction.model.EngineerAllocation;
import org.construction.model.EngineerMaster;
import org.construction.model.SiteMaster;
import org.construction.repo.EngineerAllocationRepo;
import org.construction.repo.SiteMasterRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EngineerAllocationServiceImpl implements IEngineerAllocationService{
    private final EngineerAllocationRepo engineerAllocationRepo;
    private final SiteMasterRepo siteMasterRepo;
    private final ModelMapper modelMapper;
    @Autowired
    public EngineerAllocationServiceImpl(EngineerAllocationRepo engineerAllocationRepo, SiteMasterRepo siteMasterRepo, ModelMapper modelMapper) {
        this.engineerAllocationRepo = engineerAllocationRepo;
        this.siteMasterRepo = siteMasterRepo;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public EngineerMasterDto getAllocatedEngineerInfo(Long siteMasterId) {
        SiteMaster siteMaster = siteMasterRepo.findById(siteMasterId).get();
        EngineerAllocation engineerAllocation = engineerAllocationRepo.findBySiteMaster(siteMaster);
        EngineerMaster engineerMaster = engineerAllocation.getEngineerMaster();
        return modelMapper.map(engineerMaster, EngineerMasterDto.class);
    }
}
