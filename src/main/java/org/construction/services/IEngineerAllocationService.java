package org.construction.services;

import org.construction.dto.EngineerMasterDto;

import java.util.List;

public interface IEngineerAllocationService {
    EngineerMasterDto getAllocatedEngineerInfo(Long siteMasterId);
}
