package org.construction.model;

import javax.persistence.*;
@Entity
@Table(name="engineer_allocation", schema = "site_construction")
public class EngineerAllocation {
    @Id
    @SequenceGenerator(name = "engineer_allocation_id_generator", sequenceName = "site_construction.engineer_allocation_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "engineer_allocation_id_generator")
    @Column(name = "engineer_allocation_id", nullable = false)
    private long engineerAllocationId;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="engineer_master_id", referencedColumnName="engineer_master_id")
    })
    private EngineerMaster engineerMaster;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="site_master_id", referencedColumnName="site_master_id")
    })
    private SiteMaster siteMaster;

    public long getEngineerAllocationId() {
        return engineerAllocationId;
    }

    public void setEngineerAllocationId(long engineerAllocationId) {
        this.engineerAllocationId = engineerAllocationId;
    }

    public EngineerMaster getEngineerMaster() {
        return engineerMaster;
    }

    public void setEngineerMaster(EngineerMaster engineerMaster) {
        this.engineerMaster = engineerMaster;
    }

    public SiteMaster getSiteMaster() {
        return siteMaster;
    }

    public void setSiteMaster(SiteMaster siteMaster) {
        this.siteMaster = siteMaster;
    }
}
