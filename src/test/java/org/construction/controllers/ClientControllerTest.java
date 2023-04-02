package org.construction.controllers;

import org.construction.dto.ClientMasterDto;
import org.construction.services.IClientMasterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;


public class ClientControllerTest {

    private IClientMasterService clientMasterService = Mockito.mock(IClientMasterService.class);
    private ClientController clientController = new ClientController(clientMasterService);
    protected MockMvc mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();

    @Test
    public void testCreateClient() throws Exception {
        MockMultipartFile clientPhoto = new MockMultipartFile("clientPhoto", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "test".getBytes());

        mockMvc.perform(multipart("/create-client")
                        .file(clientPhoto)
                        .param("clientName", "Test Client")
                        .param("clientAddress", "123 Test Street")
                        .param("clientMobileNumber", "1234567890")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(clientMasterService, times(1)).createClient("Test Client", "123 Test Street", "1234567890", clientPhoto);
    }

    @Test
    public void testUpdateClient() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("clientPhoto", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "test".getBytes());
        MockMultipartHttpServletRequestBuilder multipart = (MockMultipartHttpServletRequestBuilder) multipart("/update-client").with(request -> {
            request.setMethod("PUT");
            return request;
        });
        ResultActions perform = mockMvc.perform(multipart
                .file(multipartFile)
                .param("clientId", "1")
                .param("clientName", "Test Client")
                .param("clientAddress", "123 Test Street")
                .param("clientMobileNumber", "1234567890")
                .param("clientProgressMasterId", "1")
                .param("clientProgressStatusId", "2")
                .contentType(MediaType.MULTIPART_FORM_DATA));

        perform.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"message\": \"CREATED\"}"));
        verify(clientMasterService, times(1)).updateClient(
                eq(1L),
                eq("Test Client"),
                eq("123 Test Street"),
                eq("1234567890"),
                any(MultipartFile.class),
                eq(1L),
                eq(2L)
        );
    }

    @Test
    public void testGetClient() throws Exception {
        ClientMasterDto clientMasterDto = new ClientMasterDto();
        clientMasterDto.setClientId(1L);
        clientMasterDto.setClientName("Test Client");
        clientMasterDto.setClientAddress("123 Test Street");
        clientMasterDto.setClientMobNbr("1234567890");

        when(clientMasterService.getClientInfo(1L)).thenReturn(clientMasterDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/client-info")
                        .param("clientId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientName").value("Test Client"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientAddress").value("123 Test Street"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientMobNbr").value("1234567890"));

        verify(clientMasterService, times(1)).getClientInfo(1L);
    }
}
