package org.construction.dto;

import lombok.Data;

@Data
public class SupervisorMasterDto {
    private long supervisorMasterId;
    private String supervisorName;
    private String supervisorAddress;
    private String supervisorMobNbr;
    private byte[] supervisorPhoto;
}
