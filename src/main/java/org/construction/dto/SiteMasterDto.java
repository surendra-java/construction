package org.construction.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;

@Data
public class SiteMasterDto {
    private long siteMasterId;
    private String siteName;
    private String siteAddress;

    private String siteWard;
    private String siteCity;
    private String sitePin;

    private byte[] sitePhoto;
}
