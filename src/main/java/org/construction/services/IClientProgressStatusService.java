package org.construction.services;

import org.construction.dto.ClientProgressStatusDto;

import java.util.List;

public interface IClientProgressStatusService {
    List<ClientProgressStatusDto> getAllInitialProgressStatus();
}
