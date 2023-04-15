package org.construction.model;

import javax.persistence.*;

@Entity
@Table(name="site_master", schema = "site_construction")
public class SiteMaster {
    @Id
    @SequenceGenerator(name = "site_master_id_generator", sequenceName = "site_construction.site_master_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "site_master_id_generator")
    @Column(name = "site_master_id", nullable = false)
    private long siteMasterId;
    @Column(name = "site_name")
    private String siteName;
    @Column(name = "site_address")
    private String siteAddress;

    @Column(name = "site_pin")
    private String sitePin;

    @Column(name = "site_ward")
    private String siteWard;

    @Column(name = "site_city")
    private String siteCity;

    @Lob
    @Column(name = "site_photo")
    private byte[] sitePhoto;

    public long getSiteMasterId() {
        return siteMasterId;
    }

    public void setSiteMasterId(long siteMasterId) {
        this.siteMasterId = siteMasterId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public String getSitePin() {
        return sitePin;
    }

    public void setSitePin(String sitePin) {
        this.sitePin = sitePin;
    }

    public String getSiteWard() {
        return siteWard;
    }

    public void setSiteWard(String siteWard) {
        this.siteWard = siteWard;
    }

    public String getSiteCity() {
        return siteCity;
    }

    public void setSiteCity(String siteCity) {
        this.siteCity = siteCity;
    }

    public byte[] getSitePhoto() {
        return sitePhoto;
    }

    public void setSitePhoto(byte[] sitePhoto) {
        this.sitePhoto = sitePhoto;
    }
}
