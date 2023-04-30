package org.construction.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="supervisor_master", schema = "site_construction")
public class SupervisorMaster {
    @Id
    @SequenceGenerator(name = "supervisor_master_id_generator", sequenceName = "site_construction.supervisor_master_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supervisor_master_id_generator")
    @Column(name = "supervisor_master_id", nullable = false)
    private long supervisorMasterId;
    @Column(name = "supervisor_name")
    private String supervisorName;
    @Column(name = "supervisor_address")
    private String supervisorAddress;
    @Column(name = "supervisor_mob")
    private String supervisorMobNbr;

    @Lob
    @Column(name = "supervisor_photo")
    private byte[] supervisorPhoto;

    @OneToMany(mappedBy="supervisorMaster")
    private List<SupervisorAllocation> supervisorAllocations;

    public long getSupervisorMasterId() {
        return supervisorMasterId;
    }

    public void setSupervisorMasterId(long supervisorMasterId) {
        this.supervisorMasterId = supervisorMasterId;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getSupervisorAddress() {
        return supervisorAddress;
    }

    public void setSupervisorAddress(String supervisorAddress) {
        this.supervisorAddress = supervisorAddress;
    }

    public String getSupervisorMobNbr() {
        return supervisorMobNbr;
    }

    public void setSupervisorMobNbr(String supervisorMobNbr) {
        this.supervisorMobNbr = supervisorMobNbr;
    }

    public byte[] getSupervisorPhoto() {
        return supervisorPhoto;
    }

    public void setSupervisorPhoto(byte[] supervisorPhoto) {
        this.supervisorPhoto = supervisorPhoto;
    }

    public List<SupervisorAllocation> getSupervisorAllocations() {
        return supervisorAllocations;
    }

    public void setSupervisorAllocations(List<SupervisorAllocation> supervisorAllocations) {
        this.supervisorAllocations = supervisorAllocations;
    }
}
