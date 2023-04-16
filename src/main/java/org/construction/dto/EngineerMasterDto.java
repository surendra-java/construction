package org.construction.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;

@Data
public class EngineerMasterDto {
    private long engineerMasterId;
    private String engineerName;
    private String engineerAddress;
    private String engineerMobNbr;
    private byte[] engineerPhoto;
}
