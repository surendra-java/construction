package org.construction.services;

import org.construction.dto.ClientProgressDto;

import java.util.List;

public interface IClientProgressService {
    List<ClientProgressDto> getClientProgress(Long clientId);
}
