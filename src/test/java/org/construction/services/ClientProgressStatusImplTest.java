package org.construction.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.construction.dto.ClientProgressStatusDto;
import org.construction.model.ClientProgressStatus;
import org.construction.repo.ClientProgressStatusRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class ClientProgressStatusImplTest {

    @Mock
    private ClientProgressStatusRepo clientProgressStatusRepo;

    @Mock
    private ModelMapper modelMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllInitialProgressStatus() {
        List<ClientProgressStatus> clientProgressesStatus = new ArrayList<>();
        ClientProgressStatus clientProgressStatus = new ClientProgressStatus();
        clientProgressStatus.setClientProgressStatusId(1L);
        clientProgressStatus.setClientProgressStatusName("Initial Status");
        clientProgressesStatus.add(clientProgressStatus);

        List<ClientProgressStatusDto> clientProgressStatusDtoList = new ArrayList<>();
        ClientProgressStatusDto clientProgressStatusDto = new ClientProgressStatusDto();
        clientProgressStatusDto.setClientProgressStatusId(1L);
        clientProgressStatusDto.setClientProgressStatusName("Initial Status");
        clientProgressStatusDtoList.add(clientProgressStatusDto);

        when(clientProgressStatusRepo.findAll()).thenReturn(clientProgressesStatus);

        when(modelMapper.map(clientProgressStatus, ClientProgressStatusDto.class)).thenReturn(clientProgressStatusDto);
        ClientProgressStatusImpl clientProgressStatusImpl = new ClientProgressStatusImpl(clientProgressStatusRepo, modelMapper);
        List<ClientProgressStatusDto> actualResult = clientProgressStatusImpl.getAllInitialProgressStatus();

        assertEquals(clientProgressStatusDtoList.size(), actualResult.size());
        assertEquals(clientProgressStatusDtoList.get(0).getClientProgressStatusId(), actualResult.get(0).getClientProgressStatusId());
        assertEquals(clientProgressStatusDtoList.get(0).getClientProgressStatusName(), actualResult.get(0).getClientProgressStatusName());
    }

}
