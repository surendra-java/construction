package org.construction.model;

import javax.persistence.*;

@Entity
@Table(name="engineer_master", schema = "site_construction")
public class EngineerMaster {
    @Id
    @SequenceGenerator(name = "engineer_master_id_generator", sequenceName = "site_construction.engineer_master_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "engineer_master_id_generator")
    @Column(name = "engineer_master_id", nullable = false)
    private long engineerMasterId;
    @Column(name = "engineer_name")
    private String engineerName;
    @Column(name = "engineer_address")
    private String engineerAddress;
    @Column(name = "engineer_mob")
    private String engineerMobNbr;
    @Lob
    @Column(name = "engineer_photo")
    private byte[] engineerPhoto;
    public long getEngineerMasterId() {
        return engineerMasterId;
    }

    public void setEngineerMasterId(long engineerMasterId) {
        this.engineerMasterId = engineerMasterId;
    }

    public String getEngineerName() {
        return engineerName;
    }

    public void setEngineerName(String engineerName) {
        this.engineerName = engineerName;
    }

    public String getEngineerAddress() {
        return engineerAddress;
    }

    public void setEngineerAddress(String engineerAddress) {
        this.engineerAddress = engineerAddress;
    }

    public String getEngineerMobNbr() {
        return engineerMobNbr;
    }

    public void setEngineerMobNbr(String engineerMobNbr) {
        this.engineerMobNbr = engineerMobNbr;
    }

    public byte[] getEngineerPhoto() {
        return engineerPhoto;
    }

    public void setEngineerPhoto(byte[] engineerPhoto) {
        this.engineerPhoto = engineerPhoto;
    }

}
