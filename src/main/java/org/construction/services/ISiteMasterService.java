package org.construction.services;

import org.construction.dto.SiteMasterDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ISiteMasterService {
    void createSite(Long client, Long engineer, Long supervisor, String siteName, String siteAddress, String siteWard, String siteCity, String sitePin,   MultipartFile sitePhoto) throws IOException;

    List<SiteMasterDto> getSitesInfo();

    void deleteSite(Long siteMasterId);

    SiteMasterDto getSiteInfo(Long siteMasterId);

    void updateSite(Long siteMasterId, String siteName, String siteAddress, String siteWard, String siteCity, String sitePin, MultipartFile sitePhoto) throws IOException;
}
