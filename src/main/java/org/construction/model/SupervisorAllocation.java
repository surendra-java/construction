package org.construction.model;

import javax.persistence.*;

@Entity
@Table(name="supervisor_allocation", schema = "site_construction")
public class SupervisorAllocation {
    @Id
    @SequenceGenerator(name = "supervisor_allocation_id_generator", sequenceName = "site_construction.supervisor_allocation_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supervisor_allocation_id_generator")
    @Column(name = "supervisor_allocation_id", nullable = false)
    private long supervisorAllocationId;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="supervisor_master_id", referencedColumnName="supervisor_master_id")
    })
    private SupervisorMaster supervisorMaster;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="site_master_id", referencedColumnName="site_master_id")
    })
    private SiteMaster siteMaster;

    public long getSupervisorAllocationId() {
        return supervisorAllocationId;
    }

    public void setSupervisorAllocationId(long supervisorAllocationId) {
        this.supervisorAllocationId = supervisorAllocationId;
    }

    public SupervisorMaster getSupervisorMaster() {
        return supervisorMaster;
    }

    public void setSupervisorMaster(SupervisorMaster supervisorMaster) {
        this.supervisorMaster = supervisorMaster;
    }

    public SiteMaster getSiteMaster() {
        return siteMaster;
    }

    public void setSiteMaster(SiteMaster siteMaster) {
        this.siteMaster = siteMaster;
    }
}
