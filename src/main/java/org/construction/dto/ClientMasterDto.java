package org.construction.dto;

import lombok.Data;

@Data
public class ClientMasterDto {
    private Long clientId;

    private String clientName;

    private String clientAddress;

    private String clientMobNbr;

    private Long clientProgressMasterId;

    private Long clientProgressStatusId;

    private byte[] clientPhoto;

}
